package task.service;

import task.model.EmailConfig;


/**
 * @author Gavin
 */
public interface QuratzService {
	/**
	 * 閫氳繃trigger name鑾峰彇璇rigger鐨勬墽琛屾椂闂�
	 * @param triggerName
	 * @return
	 */
	public EmailConfig getCronExpression();
	
}
