package task.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import task.model.Task;
import task.model.TaskGroup;

public class EmailUtil {
	private Map<Integer,TaskGroup> map = new HashMap<Integer,TaskGroup>();
	
	private List<Task> tasks = new ArrayList<Task>();
	
	public void add(Task task){
		if(map.get(task.getTeam())==null){
			map.put(task.getTeam(), new TaskGroup(task.getTeam()));
		}
		map.get(task.getTeam()).addTask(task);
		tasks.add(task);
	}
	
	public void add(List<Task> tasks){
		for (Task task : tasks) {
			this.add(task);
		}
	}
	
	public Collection<TaskGroup> getTaskGroup(){
		return map.values();
	}
	
	public Collection<List<Task>> getTaskGoupbyUser(){
		Map<Integer,List<Task>> map = new HashMap<Integer,List<Task>>();
		for (Task task : tasks) {
			List<Task> ts = map.get(task.getOwner());
			if(ts==null){
				ts = new ArrayList<Task>();
				map.put(task.getOwner(), ts);
			}
			ts.add(task);
		}
		return map.values();
	}
}
