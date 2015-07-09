package task.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import task.dao.EmailConfigMapper;
import task.model.EmailConfig;
import task.service.QuratzService;

@Service
public class QuratzServiceImpl implements QuratzService {
	
	@Autowired
	private EmailConfigMapper emailConfigMapper;
	
	@Override
	@Cacheable(value="emailconfig")
	public EmailConfig getCronExpression() {
		return this.emailConfigMapper.getEmailConfig();
	}
	
	
	
}
