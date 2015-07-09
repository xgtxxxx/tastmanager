package task.mail.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import task.mail.MailService;
import task.model.EmailConfig;
import task.model.EmailLog;
import task.model.NoticeModel;
import task.model.User;
import task.service.EmailConfigService;
import task.service.EmailLogService;
import task.util.DateUtil;



/**
 * Class for sending e-mail messages based on Velocity templates or with
 * attachments.
 * 
 */
public class MailServiceImpl implements MailService,Observer {

	@Autowired
	private EmailLogService emailLogService;
	@Autowired
	private EmailConfigService emailConfigService;
	/**
	 * common
	 */
	private JavaMailSenderImpl mailSender;
	private VelocityEngine velocityEngine;
	
	private String defaultTemplate;
	private String noticeTemplate;
	private String m_template;
	
	public void init(){
		this.emailConfigService.addObserver(this);
		EmailConfig config = this.emailConfigService.getEmailConfig();
		this.reInit(config);
	}
	
	public void reInit(EmailConfig config){
		mailSender = new JavaMailSenderImpl();
		mailSender.setHost(config.getHost());
		mailSender.setPort(config.getPort());
		mailSender.setPassword(config.getPassword());
		mailSender.setUsername(config.getUserName());
		mailSender.setDefaultEncoding("UTF-8");
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.smtp.auth", String.valueOf(config.isAuth()));
		javaMailProperties.setProperty("mail.smtp.starttls.enable", String.valueOf(config.isStartTls()));
		mailSender.setJavaMailProperties(javaMailProperties);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.reInit((EmailConfig)arg);
	}
	
	public void setDefaultTemplate(String defaultTemplate) {
		this.defaultTemplate = defaultTemplate;
	}


	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	
	/**
	 * Send a simple message based on a Velocity template.
	 * 
	 * @param msg
	 *            the message to populate
	 * @param templateName
	 *            the Velocity template to use (relative to classpath)
	 * @param model
	 *            a map containing key/value pairs
	 * @throws MessagingException
	 */
	public String sendMonthlyMessage(Map<String, Object> model,User u, boolean preview) throws Exception {

		String result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, m_template,"utf-8",model);
		if(preview){
			return result;
		}
		MimeMessage mmsg = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mmsg, true,
				"utf-8");
		messageHelper.setText(result, true);
		
		EmailConfig config = this.emailConfigService.getEmailConfig();
		messageHelper.setTo(config.getMonthlyTos());
		messageHelper.setSubject(model.get("month")+" "+config.getMonthlySubject());
		messageHelper.setCc(this.append(config.getMonthlyCcs(), u.getAccount()));
		messageHelper.setFrom(config.getUserName());
		if(config.getMonthlyBcc()!=null){
			messageHelper.setBcc(config.getMonthlyBccs());
		}
		this.doSend(mmsg,u.getUsername(),u.getAccount(),EmailLog.MONTHLY_REPORT,EmailLog.MANUL_SEND,0,0);
		return null;
	}
	
	@Override
	public void sendSysMessage(Map<String, Object> model, String[] bccs) throws Exception {
		String result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, defaultTemplate,"utf-8",model);
		
		MimeMessage mmsg = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mmsg, true,
				"utf-8");
		messageHelper.setText(result, true);
		EmailConfig config = this.emailConfigService.getEmailConfig();
		messageHelper.setTo(config.getDailyTo());
		messageHelper.setSubject(config.getDailySubject());
		if(config.getDailyCcs()!=null){
			messageHelper.setCc(config.getDailyCcs());
		}
		if(bccs!=null)
			messageHelper.setBcc(bccs);
		messageHelper.setFrom(config.getUserName());
		this.doSend(mmsg,"SYSTEM",config.getUserName(),EmailLog.DAYLY_REPORT,EmailLog.AUTO_SEND,EmailLog.GROUP_SYS,0);
	}
	
	@Override
	public String sendPerDailyMessage(Map<String, Object> model, User u, boolean preview) throws Exception {
		String result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, defaultTemplate,"utf-8",model);
		if(preview){
			return result;
		}
		MimeMessage mmsg = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mmsg, true,
				"utf-8");
		messageHelper.setText(result, true);
		EmailConfig config = this.emailConfigService.getEmailConfig();
		messageHelper.setTo(config.getDailyTos());
		messageHelper.setSubject(config.getDailySubject());
		messageHelper.setCc(this.append(config.getDailyCcs(), u.getAccount()));
		if(config.getDailyBccs()!=null)
			messageHelper.setBcc(config.getDailyBccs());
		messageHelper.setFrom(config.getUserName());
		this.doSend(mmsg,u.getUsername(),u.getAccount(),EmailLog.DAYLY_REPORT,EmailLog.MANUL_SEND,EmailLog.GROUP_PER,u.getId());
		return null;
	}
	
	@Override
	public String sendTeamDailyMessage(Map<String, Object> model, User u, List<User> members, int teamId, boolean preview) throws Exception {
		String result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, defaultTemplate,"utf-8",model);
		if(preview){
			return result;
		}
		MimeMessage mmsg = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mmsg, true,"utf-8");
		messageHelper.setText(result, true);
		EmailConfig config = this.emailConfigService.getEmailConfig();
		messageHelper.setTo(config.getDailyTos());
		messageHelper.setSubject(config.getDailySubject());
		messageHelper.setCc(this.append(config.getDailyCcs(), u.getAccount()));
		String[] teamBcc = new String[members.size()];
		for (int i=0; i<members.size(); i++) {
			User user = members.get(i);
			teamBcc[i] = user.getAccount();
		}
		messageHelper.setBcc(teamBcc);
		messageHelper.setFrom(config.getUserName());
		this.doSend(mmsg,u.getUsername(),u.getAccount(),EmailLog.DAYLY_REPORT,EmailLog.MANUL_SEND,EmailLog.GROUP_TEAM,teamId);
		return null;
	}
	
	@Override
	public void sendNotice(Collection<NoticeModel> notices) throws Exception {
		for (NoticeModel notice : notices) {
			String result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, this.noticeTemplate,"utf-8",notice.getMapResult());
			MimeMessage mmsg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mmsg, true,"utf-8");
			messageHelper.setText(result, true);
			messageHelper.setTo(notice.getEmail());
			messageHelper.setSubject("You have not create task today!");
			EmailConfig config = this.emailConfigService.getEmailConfig();
			messageHelper.setFrom(config.getUserName());
			this.doSend(mmsg);
		}
	}
	
	@Override
	public void sendUserInfo(String account, long psw, String subject) throws Exception{
		String result = "Hello, your account is "+account+" and password is "+psw;
		result+=".<br/> Our home page is : <a href='http://matools.jd-app.com/login'>http://matools.jd-app.com</a>";
		MimeMessage mmsg = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mmsg, true,"utf-8");
		messageHelper.setText(result, true);
		messageHelper.setTo(account);
		messageHelper.setSubject(subject);
		EmailConfig config = this.emailConfigService.getEmailConfig();
		messageHelper.setFrom(config.getUserName());
		this.doSend(mmsg);
	}
	
	
	private void doSend(MimeMessage msg) {
		EmailConfig config = this.emailConfigService.getEmailConfig();
		if(!config.isDebug()){
			mailSender.send(msg);
		}
	}

	private void doSend(MimeMessage msg,String sender,String email,int emailType,int sendType, int group, int gid) throws Exception{
		EmailConfig config = this.emailConfigService.getEmailConfig();
		if(!config.isDebug()){
			mailSender.send(msg);
			EmailLog log = new EmailLog();
			log.setSendTime(DateUtil.getCurrentDayTime());
			log.setSender(sender);
			log.setEmailType(emailType);
			log.setSendType(sendType);
			log.setEmail(email);
			log.setGroup(group);
			log.setGroupId(gid);
			this.writeLog(log);
		}
	}
	
	public void writeLog(EmailLog log) throws Exception{
		this.emailLogService.writeLog(log);
	}
	
	private String[] append(String[] target,String param){
		if(target==null){
			target = new String[]{};
		}
		String[] tt = Arrays.copyOf(target, target.length+1);
		tt[tt.length-1] = param;
		return tt;
	}
	/**
	 * check system is sent?
	 * @return
	 * @throws Exception
	 */
	public boolean isSysSent() throws Exception{
		return this.emailLogService.isSysSent();
	}
	/**
	 * Check your task is sent?
	 * @return
	 * @throws Exception
	 */
	public boolean isSent(int userId) throws Exception{
		return this.emailLogService.isSent(userId);
	}

	public void setEmailLogService(EmailLogService emailLogService) {
		this.emailLogService = emailLogService;
	}

	public void setNoticeTemplate(String noticeTemplate) {
		this.noticeTemplate = noticeTemplate;
	}
	
	public void setM_template(String m_template) {
		this.m_template = m_template;
	}

}
