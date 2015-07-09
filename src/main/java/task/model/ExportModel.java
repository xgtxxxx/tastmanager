package task.model;

import java.util.HashMap;
import java.util.Map;


public class ExportModel {
	private String taskId;
	private String team;
	private String status;
	private String owner;
	private String startDate;
	private String endDate;
	private String submitDate;
	private String type;
	private int ownerId;
	private String isOwn;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String,Object> getParams() {
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
			map.put("team", team.split(","));
		}
		if(!isEmpty(status)){
			map.put("status", status.split(","));
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
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	@Override
	public String toString() {
		return "ExportModel [taskId=" + taskId + ", team=" + team + ", status="
				+ status + ", owner=" + owner + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", type=" + type + ", ownerId="
				+ ownerId + ", isOwn=" + isOwn + "]";
	}
	public String getIsOwn() {
		return isOwn;
	}
	public void setIsOwn(String isOwn) {
		this.isOwn = isOwn;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
}
