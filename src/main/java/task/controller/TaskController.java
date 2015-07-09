package task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import task.base.BaseController;
import task.model.ExportModel;
import task.model.ReleaseVersion;
import task.model.SearchModel;
import task.model.Task;
import task.model.User;
import task.service.ReleaseVersionService;
import task.service.TaskService;
import task.service.TeamService;
import task.util.ReportUtils;
import task.util.ReportUtils.DocType;
import task.util.StringUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Kiven
 * @date Jul 24, 2014 3:30:18 PM
 */
@Controller
@RequestMapping(value = "/task")
public class TaskController extends BaseController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private ReleaseVersionService releaseVersionService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String forMain(ModelMap modelMap) throws JsonProcessingException {
		if (authHelper.hasRole("ROLE_TEAMLEADER")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("account", authHelper.getCurrentAuthentication().getName());
			
			
			ObjectMapper objectMapper = new ObjectMapper();
			modelMap.put("teams", objectMapper.writeValueAsString(this.teamService.getTLTeams(map)));
		}
		return "/task/main";
	}

	@RequestMapping(value = "/tasklist", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTasks(SearchModel searchModel,boolean isOwn) {
		if(isOwn){
			User u = this.getCurrentUser();
			searchModel.setOwnerId(u.getId());
		}
		return taskService.listTask(searchModel);
	}
	
	@RequestMapping(value = "/listMonthlyTasks", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listMonthlyTasks() {
		Integer account = this.getCurrentUser().getId();
		return taskService.listMonthlyTasks(account);
	}

	@RequestMapping(value = "/updateTask", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateTask(Task task) {
		User u = this.getCurrentUser();
		task.setOwnerName(u.getUsername());
		task.setOwner(u.getId());
		task.setLastUpdateTime(getCurrentDatetimeString());
		task.setTaskId(task.getTaskId().trim());
		task.setDescription(task.getDescription().trim());
		String tid = task.getTaskId().toUpperCase();
		task.setTaskId(tid);
		task.setReleaseVersion(StringUtil.toUpperCaseFirst(task.getReleaseVersion()));
		taskService.updateTaskSelective(task);
		this.updateRelease(task.getReleaseVersion());
		return getSuccessMap("Update task sucess!");
	}

	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addTask(Task task) {
		User u = this.getCurrentUser();
		task.setOwnerName(u.getUsername());
		task.setOwner(u.getId());
		task.setSubmitTime(getCurrentDatetimeString());
		task.setLastUpdateTime(getCurrentDatetimeString());
		task.setTaskId(task.getTaskId().trim());
		task.setDescription(task.getDescription().trim());
		String tid = task.getTaskId().toUpperCase();
		task.setTaskId(tid);
		task.setReleaseVersion(StringUtil.toUpperCaseFirst(task.getReleaseVersion()));
		taskService.insertTaskSelective(task);
		this.updateRelease(task.getReleaseVersion());
		return this.getSuccessMap("Insert task sucess!", task);
	}
	
	private void updateRelease(String name){
		List<ReleaseVersion> ls = this.releaseVersionService.selectByName(name);
		if(ls==null||ls.size()==0){
			ReleaseVersion version = new ReleaseVersion();
			version.setActive(ReleaseVersion.ADD);
			version.setName(name);
			this.releaseVersionService.insertVersion(version);
		}
	}

	/**
	 * @param request
	 * @param modelMap
	 * @return
	 * 
	 * only set the 'enabled' false
	 */
	@RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteTask(Task task) {
		task.setEnabled(false);
		taskService.updateTaskSelective(task);

		return getSuccessMap("Delete task sucess!");
	}
	
	@RequestMapping(value = "/exportTask")
	public @ResponseBody void exportTask(HttpServletResponse response,ExportModel model) {
		try {
			DocType docType = null;
			String title = "DailyReport";
			if("excel".equals(model.getType())){
				docType = DocType.XLS;
				response.reset();
				response.setContentType("application/octet-stream");   
				response.setHeader("Content-Disposition", "attachment;filename="+title+".xlsx");
			}else if("html".equals(model.getType())){
				docType = DocType.HTML;
				response.setContentType("text/html");   
		        response.setCharacterEncoding("UTF-8"); 
			}else{
				docType = DocType.PDF;
				response.setContentType("application/pdf");  
			    response.setCharacterEncoding("UTF-8");    
			    response.setHeader("Content-Disposition", "attachment; filename="+title+".pdf");  
			}
			if("true".equals(model.getIsOwn())){
				User u = this.getCurrentUser();
				model.setOwnerId(u.getId());
			}
			List<Task> data = this.taskService.getExportTasks(model.getParams());
			Map<String,Object> parameters = new HashMap<String,Object>();
		    parameters.put("title", "Daily Report");
			ReportUtils.export(response.getOutputStream(), parameters, data, docType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/exportMonthlyTask")
	public @ResponseBody void exportMonthlyTask(HttpServletResponse response,String type) {
		try {
			DocType docType = null;
			String title = "MonthlyTask";
			if("excel".equals(type)){
				docType = DocType.XLS;
				response.reset();
				response.setContentType("application/octet-stream");   
				response.setHeader("Content-Disposition", "attachment;filename="+title+".xlsx");
			}else if("html".equals(type)){
				docType = DocType.HTML;
				response.setContentType("text/html");   
		        response.setCharacterEncoding("UTF-8"); 
			}else{
				docType = DocType.PDF;
				response.setContentType("application/pdf");  
			    response.setCharacterEncoding("UTF-8");    
			    response.setHeader("Content-Disposition", "attachment; filename="+title+".pdf");  
			}
			List<Task> data = this.taskService.getExportTasks(this.getCurrentUser().getId());
			Map parameters = new HashMap();
		    parameters.put("title", "Monthly Task");
			ReportUtils.export(response.getOutputStream(), parameters, data, docType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
