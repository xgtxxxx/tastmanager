package task.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task.base.BaseService;
import task.dao.BaseDao;
import task.dao.TaskMapper;
import task.mail.MailService;
import task.model.Pager;
import task.model.SearchModel;
import task.model.Task;
import task.service.TaskService;
import task.util.DateUtil;

@Service
public class TaskServiceImpl extends BaseService implements TaskService {
	@Autowired
	private TaskMapper taskMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private MailService mailService;

	public void insertTaskSelective(Task task) {
		taskMapper.insertSelective(task);
	}

	public void updateTaskSelective(Task task) {
		taskMapper.updateByPrimaryKeySelective(task);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getAll(Task task) {
//		return queryForPagerJsonString(taskMapper.selectAllTask(task), taskMapper.selectAllTaskCount(task));
		Pager pager = new Pager();
		pager.setPageNo(task.getStart());
		pager.setPageSize(task.getLimit());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("owner", task.getOwner());
		map.put("taskId", task.getTaskId());
		map.put("status", task.getStatus());
		
		Pager p = this.baseDao.findPagination("task.dao.TaskMapper.selectAllTaskPage", map, pager);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("recordlist", p.getResultList());
		data.put("totalcount", p.getRowCount());
		return data;
	}

	@Override
	public Map<String, Object> listMonthlyTasks(Integer account) {
		Map<String, Object> data = new HashMap<String, Object>();
		Date curr = new Date();
		String s = DateUtil.format(curr, "yyyy-MM");
		StringBuffer sb = new StringBuffer();
		sb.append(s).append("-01 00:00:00");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		map.put("startTime", sb.toString());
		data.put("recordlist", this.taskMapper.listMonthlyTasks(map));
		return data;
	}
	
	public Map<String, Object> getAll() {
		return queryForPagerJsonString(taskMapper.selectAllTask(null), taskMapper.selectAllTaskCount(null));
	}

	public List<Task> getEmailTask(Map<String, Object> whereMap) {
		return taskMapper.selectEmailTask(whereMap);
	}


	@Override
	public List<Task> getExportTasks(Map<String,Object> params) {
		List<Task> data = this.taskMapper.selectAllTask(params);
	    int i = 1;
	    for (Task t : data) {
			t.setRowNum(i++);
		}
	    return data;
	}

	@Override
	public Map<String, Object> listTask(SearchModel searchModel) {
//		Pager pager = new Pager();
//		pager.setPageNo(searchModel.getStart());
//		pager.setPageSize(searchModel.getLimit());
//		Pager p = this.baseDao.findPagination("task.dao.TaskMapper.selectAllTaskPage", searchModel.getSearchMap(), pager);
		
		Map<String,Object> param = searchModel.getSearchMap();
		param.put("start", searchModel.getStart());
		param.put("limit", searchModel.getLimit());
		int count = this.taskMapper.listTaskCount(param);
		List<Task> resultList = this.taskMapper.listTask(param);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("recordlist", resultList);
		data.put("totalcount", count);
		return data;
	}

	@Override
	public List<Task> getExportTasks(Integer id) {
		Date curr = new Date();
		String s = DateUtil.format(curr, "yyyy-MM");
		StringBuffer sb = new StringBuffer();
		sb.append(s).append("-01 00:00:00");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", id);
		map.put("startTime", sb.toString());
		List<Task> data = this.taskMapper.listMonthlyTasks(map);
		int i = 1;
	    for (Task t : data) {
			t.setRowNum(i++);
		}
		return data;
	}

	@Override
	public List<Task> getTodayTaskByUserId(int userId) {
		return this.taskMapper.getTodayTaskByUserId(userId);
	}

	@Override
	public List<Task> getTodayTaskByTeamId(int teamId) {
		return this.taskMapper.getTodayTaskByTeamId(teamId);
	}

	@Override
	public List<Task> listTaskByIds(String[] ids) {
		return this.taskMapper.listTaskByIds(ids);
	}
	
	
}
