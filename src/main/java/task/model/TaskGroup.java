package task.model;

import java.util.ArrayList;
import java.util.List;


public class TaskGroup {
	
	private Integer team;
	
	private List<Task> tasks;
	
	public TaskGroup(Integer team) {
		this.team=team;
	}
	public TaskGroup() {
		super();
	}
	public TaskGroup(Integer team, List<Task> tasks) {
		super();
		this.team = team;
		this.tasks = tasks;
	}
	public Integer getTeam() {
		return team;
	}
	public void setTeam(Integer team) {
		this.team = team;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void addTask(Task task){
		if(this.tasks==null)
			this.tasks = new ArrayList<Task>();
		this.tasks.add(task);
	}
	
}