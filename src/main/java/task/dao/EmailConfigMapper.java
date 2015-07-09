package task.dao;

import task.model.EmailConfig;

public interface EmailConfigMapper {
	
	public void updateConfig(EmailConfig config);
	
	public EmailConfig getEmailConfig();

	public void saveChange(EmailConfig config);
}
