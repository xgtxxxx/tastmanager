package task.service;

import task.model.EmailLog;

/**
 * @author Gavin
 */
public interface EmailLogService {
	/**
	 * @param log
	 * @throws Exception
	 */
	public void writeLog(EmailLog log) throws Exception;
	/**
	 * check system is sent?
	 * @return
	 * @throws Exception
	 */
	public boolean isSysSent() throws Exception;
	
	/**
	 * Check your task is sent?
	 * @return
	 * @throws Exception
	 */
	public boolean isSent(int userId) throws Exception;
	
}
