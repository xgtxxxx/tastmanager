package task.dao;

import task.model.EmailLog;

public interface EmailLogMapper {
	
	public void writeLog(EmailLog log);
	
	public int selectSysCount();
	
	public int selectTeamCount(int[] team);
}
