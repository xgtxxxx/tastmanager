package task.service;

import java.util.Observer;

import task.model.EmailConfig;


/**
 * @author Gavin
 */
public interface EmailConfigService {
	public EmailConfig getEmailConfig();

	public void saveEmailConfig(EmailConfig config) throws Exception;
	
	public void addObserver(Observer o);
}
