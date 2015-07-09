package task.model;

import java.io.Serializable;
import java.util.Date;

import task.base.BaseModel;
import task.util.DateUtil;

@SuppressWarnings("serial")
public class Task extends BaseModel implements Serializable {
	private Integer id;
	
	private int rowNum;

	private String taskId;

	private String description;

//	private Integer priority;

	private Integer team;
	
	private Integer status;
	
	private boolean checked;

	private String assignDate;

	private String fixedDate;

	private Integer owner;
	
	private String ownerName;
	
	private String ownerEmail;

//	private String comments;
//
//	private String questions;

	private String submitTime;

	private String lastUpdateTime;

	private Boolean enabled;

	private String teamName;

	private String statusDesc;
	
	private String fontColor;
	
	private String bgColor;
	
	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	private String releaseVersion;
	
	private double progress;
	
	private String feedback;
	
	private String pendingIssues;
	
	public String getPendingIssues() {
		return pendingIssues;
	}

	public void setPendingIssues(String pendingIssues) {
		this.pendingIssues = pendingIssues;
	}

	private String commentsAndQuestions;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId == null ? null : taskId.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

//	public Integer getPriority() {
//		return priority;
//	}
//
//	public void setPriority(Integer priority) {
//		this.priority = priority;
//	}
//
//	public Integer getTeam() {
//		return team;
//	}
//
//	public void setTeam(Integer team) {
//		this.team = team;
//	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}

	public String getFixedDate() {
		return fixedDate;
	}

	public void setFixedDate(String fixedDate) {
		this.fixedDate = fixedDate;
	}


//	public String getComments() {
//		return comments;
//	}
//
//	public void setComments(String comments) {
//		this.comments = comments == null ? null : comments.trim();
//	}
//
//	public String getQuestions() {
//		return questions;
//	}
//
//	public void setQuestions(String questions) {
//		this.questions = questions == null ? null : questions.trim();
//	}

	public String getSubmitTime() {
		return submitTime;
	}

	public String getReleaseVersion() {
		return releaseVersion;
	}

	public void setReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	public String getCommentsAndQuestions() {
		return commentsAndQuestions;
	}

	public void setCommentsAndQuestions(String commentsAndQuestions) {
		this.commentsAndQuestions = commentsAndQuestions;
	}

	public void setSubmitTime(String submitTime) {
		if(submitTime!=null&&!"".equals(submitTime)){
			Date d = DateUtil.parseDateTime(submitTime);
			if(DateUtil.formatDate(d).equals(DateUtil.getCurrentDay())){
				this.checked = true;
			}
		}
		this.submitTime = submitTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getTeam() {
		return team;
	}

	public void setTeam(Integer team) {
		this.team = team;
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskId=" + taskId + ", description="
				+ description + ", team=" + team + ", status=" + status
				+ ", checked=" + checked + ", assignDate=" + assignDate
				+ ", fixedDate=" + fixedDate + ", owner=" + owner
				+ ", ownerName=" + ownerName + ", submitTime=" + submitTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", enabled=" + enabled
				+ ", teamName=" + teamName + ", statusDesc=" + statusDesc
				+ ", fontColor=" + fontColor + ", bgColor=" + bgColor
				+ ", releaseVersion=" + releaseVersion + ", progress="
				+ progress + ", feedback=" + feedback + ", pendingIssues="
				+ pendingIssues + ", commentsAndQuestions="
				+ commentsAndQuestions + "]";
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
}