package task.model;
/**
 * 邮件发送相关日志
 * @author Gavin
 *
 */
public class EmailLog {
	public static final int DAYLY_REPORT = 1;
	public static final int MONTHLY_REPORT = 2;
	public static final int AUTO_SEND=1;
	public static final int MANUL_SEND=2;
	public static final int GROUP_PER=1;
	public static final int GROUP_TEAM=2;
	public static final int GROUP_SYS=3;
	private int id;
	private String sendTime;
	private int emailType;//1:dayly,2:monthly
	private int sendType;//1：AUTO,2：MANUL
	private String sender;
	private String email;
	private int group;
	private int groupId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getEmailType() {
		return emailType;
	}
	public void setEmailType(int emailType) {
		this.emailType = emailType;
	}
	public int getSendType() {
		return sendType;
	}
	public void setSendType(int sendType) {
		this.sendType = sendType;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "EmailLog [id=" + id + ", sendTime=" + sendTime + ", emailType="
				+ emailType + ", sendType=" + sendType + ", sender=" + sender
				+ ", email=" + email + "]";
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
