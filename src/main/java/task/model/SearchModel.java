package task.model;

import java.util.HashMap;
import java.util.Map;

public class SearchModel {
	private int start;
	private int limit;
	private String taskId;
	private int[] team;
	private int[] status;
	private String owner;
	private String startDate;
	private String endDate;
	private String submitDate;
	private String type;
	private int ownerId;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public int[] getTeam() {
		return team;
	}
	public void setTeam(int[] team) {
		this.team = team;
	}
	public int[] getStatus() {
		return status;
	}
	public void setStatus(int[] status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Map<String,Object> getSearchMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!isEmpty(taskId)){
			map.put("taskId", taskId);
		}
		if(!isEmpty(startDate)){
			map.put("startDate", startDate);
		}
		if(!isEmpty(endDate)){
			map.put("endDate", endDate);
		}
		if(!isEmpty(submitDate)){
			map.put("submitDate", submitDate);
		}
		if(!isEmpty(team)){
			map.put("team", team);
		}
		if(!isEmpty(status)){
			map.put("status", status);
		}
		if(!isEmpty(owner)){
			map.put("owner", owner);
		}
		if(ownerId!=0){
			map.put("ownerId", ownerId);
		}
		return map;
	}
	
	private boolean isEmpty(String s){
		if(s==null || "".equals(s)){
			return true;
		}else{
			return false;
		}
	}
	private boolean isEmpty(int[] os){
		if(os==null || os.length==0){
			return true;
		}else{
			return false;
		}
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
}
