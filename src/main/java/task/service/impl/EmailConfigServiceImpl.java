package task.service.impl;


import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import task.cron.schedule.EmailSchedule;
import task.dao.EmailConfigMapper;
import task.model.EmailConfig;
import task.service.EmailConfigService;

@Service("emailConfigService")
public class EmailConfigServiceImpl extends Observable implements EmailConfigService {
	
	@Autowired
	private EmailSchedule emailSchedule;
	@Autowired
	private EmailConfigMapper emailConfigMapper;
	
	@Override
	@Cacheable(value="emailconfig")
	public EmailConfig getEmailConfig() {
		return emailConfigMapper.getEmailConfig();
	}

	@Override
	@CacheEvict(value="emailconfig",allEntries=true)
	public void saveEmailConfig(EmailConfig config) throws Exception{
		this.emailConfigMapper.saveChange(config);
		if(config.getDailyTime()!=null){
				this.emailSchedule.resetTrigger(config.getDailyTime(), "sendEmail");
		}
		if(config.getNoticeTime()!=null){
			this.emailSchedule.resetTrigger(config.getNoticeTime(), "sendNotice");
		}
		if(config.getHost()!=null||config.getPort()!=null||config.getUserName()!=null||config.getPassword()!=null||config.isAuth()!=null||config.isStartTls()!=null){
			config = emailConfigMapper.getEmailConfig();
			this.setChanged();
			this.notifyObservers(config);
		}
	}

}
