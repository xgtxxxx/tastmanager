package task.cron;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import task.dao.BaseDao;
import task.mail.MailService;
import task.model.NoticeModel;
import task.model.Task;
import task.model.TaskGroup;
import task.model.Team;
import task.model.User;
import task.util.EmailUtil;

/**
 * @author Kiven
 * @date Jul 24, 2014 3:30:38 PM
 */
//@Component("sendTask")
public class SendMailTaskCron {
	private Logger logger = Logger.getLogger(getClass());
	
	private MailService mailService;

	@SuppressWarnings("rawtypes")
	private BaseDao baseDao;
	
	@SuppressWarnings("rawtypes")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	@SuppressWarnings("unchecked")
	private List<Task> getEmailTasks() throws Exception{
		return this.baseDao.getAll("task.dao.TaskMapper.selectEmailTask");
	}
	
	/**
	 * Send email task
	 */
	public void work(){
		try {
			List<Task> l = this.getEmailTasks();
			if(l==null || l.size()<=0){
				logger.info("There's no Task to send!");
				return;
			}
			Set<Integer> tids = new HashSet<Integer>();
			for (Task task : l) {
				tids.add(task.getTeam());
			}
			String[] bcc = this.getBccUsers(tids);
			
			EmailUtil tu = new EmailUtil();
			tu.add(l);
			Map<String,Object> m = new HashMap<String,Object>();
			Collection<TaskGroup> taskGroups = tu.getTaskGroup();
			m.put("taskGroups", taskGroups);
			m.put("showTeam", taskGroups.size()>1?true:false);
			this.mailService.sendSysMessage(m,bcc);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	@SuppressWarnings("unchecked")
	private String[] getBccUsers(Set<Integer> tids) throws Exception{
		List<User> us = this.baseDao.getList("task.dao.UserMapper.selectByTeams", tids);
		String[] bcc = new String[us.size()];
		for (int i=0; i<us.size(); i++) {
			bcc[i] = us.get(i).getAccount();
		}
		return bcc;
	}

	/**
	 * Send notice email
	 */
	public void notice(){
		Map<String,NoticeModel> notices = new HashMap<String,NoticeModel>();
		try {
			List<Team> teams = this.selectTeamForNotice();
			for (Team team : teams) {
				List<User> users = this.selectNoTaskUser(team);
				for (User user : users) {
					NoticeModel notice = notices.get(user.getAccount());
					if(notice==null){
						notice = new NoticeModel(user.getAccount());
						notices.put(user.getAccount(), notice);
					}
					notice.addTeam(team.getName());
				}
			}
			Collection<NoticeModel> result = notices.values();
			if(result.size()<=0){
				return;
			}
			this.mailService.sendNotice(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Team> selectTeamForNotice() throws Exception{
		return this.baseDao.getAll("task.dao.TeamMapper.selectTeamForNotice");
	}
	
	@SuppressWarnings("unchecked")
	private List<User> selectNoTaskUser(Team team) throws Exception{
		return this.baseDao.getAll("task.dao.UserMapper.selectNoTaskByTeam", team.getId());
	}
	
}
