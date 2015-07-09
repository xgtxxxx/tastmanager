package task.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import task.base.BaseController;
import task.mail.MailService;
import task.model.Task;
import task.model.TaskGroup;
import task.model.User;
import task.service.TaskService;
import task.service.UserService;
import task.util.DateUtil;
import task.util.EmailUtil;

import com.alibaba.druid.support.json.JSONUtils;

@Controller
@RequestMapping(value = "/email")
public class EmailController extends BaseController {
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "/checkSysSend")
	public @ResponseBody Map<String,Object> checkSysSend() throws Exception{
		boolean isSent = this.mailService.isSysSent();
		if(isSent){
			return getMessageMap(false, "Daily report has been sent today!<br/>Do you want resend now?");
		}else{
			return getMessageMap(true, "Send daily report now?");
		}
	}
	
	@RequestMapping(value = "/checkSend")
	public @ResponseBody Map<String,Object> checkSend() throws Exception{
		User u = this.getCurrentUser();
		boolean isSent = this.mailService.isSent(u.getId());
		if(isSent){
			return getMessageMap(true, "");
		}else{
			return getMessageMap(false, "");
		}
	}
	
	@RequestMapping(value = "/sendPerEmail")
	public @ResponseBody Map<String, Object> sendEmail(boolean preview) {
		User u = this.getCurrentUser();
		try {
			List<Task> tasks = this.taskService.getTodayTaskByUserId(u.getId());
			if(tasks.size()==0){
				return getFailureMap("There's no task to send!");
			}
					
			EmailUtil tu = new EmailUtil();
			tu.add(tasks);
			Map<String,Object> m = new HashMap<String,Object>();
			Collection<TaskGroup> taskGroups = tu.getTaskGroup();
			m.put("taskGroups", taskGroups);
			m.put("showTeam", false);
			String result = this.mailService.sendPerDailyMessage(m,u,preview);
			return getSuccessMap(result==null?"Send successfully!":result);
		} catch (Exception e) {
			e.printStackTrace();
			return getFailureMap("Send error!");
		}
	}
	
	@RequestMapping(value = "/sendTeamEmail")
	public @ResponseBody Map<String, Object> sendEmail(Integer teamId,boolean preview) {
		try {
			User u = this.getCurrentUser();
			List<User> members = this.userService.listMyUserByTeam(teamId);
			List<Task> tasks = this.taskService.getTodayTaskByTeamId(teamId);
			if(tasks.size()==0){
				return getFailureMap("There's no task to send!");
			}
			EmailUtil tu = new EmailUtil();
			tu.add(tasks);
			Collection<TaskGroup> taskGroups = tu.getTaskGroup();
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("taskGroups", taskGroups);
			m.put("showTeam", false);
			String result = this.mailService.sendTeamDailyMessage(m,u,members,teamId,preview);
			return getSuccessMap(result==null?"Send successfully!":result);
		} catch (Exception e) {
			e.printStackTrace();
			return getFailureMap("Send error!");
		}
	}
	
	@RequestMapping(value = "/sendMonthlyReport", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> sendMonthlyReport(String ids,String goals,String notes) {
		try {
			if(ids==null || "".equals(ids)){
				return getFailureMap("No task!");
			}
			this.doSend(ids,goals,notes,this.getCurrentUser(),false);
			return getSuccessMap("Send Email Sucess!");
		} catch (Exception e) {
			e.printStackTrace();
			return getFailureMap("Send Email Failure:"+e.getMessage());
		}
	}
	
	@RequestMapping(value = "/previewMonthlyReport", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> previewMonthlyReport(String ids,String goals,String notes) {
		try {
			if(ids==null || "".equals(ids)){
				return getFailureMap("No task!");
			}
			String s = this.doSend(ids,goals,notes,this.getCurrentUser(),true);
			return getSuccessMap(s);
		} catch (Exception e) {
			e.printStackTrace();
			return getFailureMap("Send Email Failure:"+e.getMessage());
		}
	}
	
	private String doSend(String ids, String goals,String notes, User u,boolean preview) throws Exception{
		List<Task> tasks = this.taskService.listTaskByIds(ids.split(","));
		Map<String,Object> map = new HashMap<String,Object>();
        Date date = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy",Locale.ENGLISH);  
		map.put("month", sdf.format(date));
		map.put("goalsMonth", DateUtil.getNextMonth("MMMM yyyy",Locale.ENGLISH));
		map.put("user", u);
		@SuppressWarnings("rawtypes")
		List l = (List)JSONUtils.parse(goals);
		map.put("goals", l);
		map.put("tasks", tasks);
		if(notes!=null&&!"".equals(notes)){
			map.put("hasNote", true);
			map.put("notes", notes);
		}
		return this.mailService.sendMonthlyMessage(map,u,preview);
	}
}
