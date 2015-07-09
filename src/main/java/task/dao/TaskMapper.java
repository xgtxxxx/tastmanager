package task.dao;

import java.util.List;
import java.util.Map;

import task.model.Task;

public interface TaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
    
    List<Task> selectAllTask(Map<String,Object> map);

    int selectAllTaskCount(Task task);
    
    List<Task> selectEmailTask(Map<String, Object> whereMap);

	List<Task> listMonthlyTasks(Map<String, Object> map);
	
	List<Task> listTaskByIds(String[] ids);

	List<Task> getTodayTaskByUserId(int userId);

	List<Task> getTodayTaskByTeamId(int teamId);

	int listTaskCount(Map<String, Object> searchMap);

	List<Task> listTask(Map<String, Object> searchMap);
	
}