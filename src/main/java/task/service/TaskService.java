package task.service;

import java.util.List;
import java.util.Map;

import task.model.SearchModel;
import task.model.Task;

public interface TaskService {

	void insertTaskSelective(Task task);

	void updateTaskSelective(Task task);

	Map<String, Object> getAll(Task task);

	Map<String, Object> getAll();

	List<Task> getEmailTask(Map<String, Object> whereMap);

	Map<String, Object> listMonthlyTasks(Integer account);

//	void sendMonthlyReport(String ids, String goals,String notes,String employee) throws Exception;
//	
//	String previewMonthlyReport(String ids, String goals,String notes,String employee) throws Exception;
	
	List<Task> getExportTasks(Map<String,Object> map);

	Map<String, Object> listTask(SearchModel searchModel);

	List<Task> getExportTasks(Integer id);
	
	List<Task> getTodayTaskByUserId(int userId);
	
	List<Task> getTodayTaskByTeamId(int teamId);

	List<Task> listTaskByIds(String[] split);
}
