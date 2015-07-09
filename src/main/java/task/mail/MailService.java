package task.mail;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import javax.mail.MessagingException;

import task.model.NoticeModel;
import task.model.User;


/**
 * Class for sending e-mail messages based on Velocity templates or with
 * attachments.
 * 
 */
public interface MailService {
	/**
	 * monthly report
	 * @throws MessagingException
	 */
	public String sendMonthlyMessage(Map<String, Object> model,User u, boolean preview) throws Exception;
	/**
	 * 瀹氭椂鍙戦�
	 * @param model
	 * @throws Exception
	 */
	public void sendSysMessage(Map<String, Object> model, String[] bcc) throws Exception;
	/**
	 * 鍙戦�閭欢鎻愰啋
	 * @param result
	 * @throws Exception
	 */
	public void sendNotice(Collection<NoticeModel> result) throws Exception;
	/**
	 * 鏂板缓鐢ㄦ埛鏃跺彂閫佹寚瀹氬瘑鐮�	 * @param account
	 * @param psw
	 * @param subject 
	 */
	public void sendUserInfo(String account, long psw, String subject) throws Exception;
	
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
	
	public String sendPerDailyMessage(Map<String, Object> m, User u,
			boolean preview) throws Exception;
	
	public String sendTeamDailyMessage(Map<String, Object> m, User u,
			List<User> members, int teamId, boolean preview) throws Exception;
	
}
