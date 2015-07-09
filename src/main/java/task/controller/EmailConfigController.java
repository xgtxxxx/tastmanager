package task.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import task.base.BaseController;
import task.model.EmailConfig;
import task.service.EmailConfigService;

@Controller
@RequestMapping(value = "/emailconfig")
public class EmailConfigController extends BaseController {
	@Autowired
	private EmailConfigService emailConfigService;
	
	@RequestMapping(value = "/getEmailConfig")
	public @ResponseBody Map<String,Object> getEmailConfig() throws Exception{
		EmailConfig config = this.emailConfigService.getEmailConfig();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("data", config);
		return result;
	}
	
	@RequestMapping(value = "/saveEmailConfig",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveEmailConfig(EmailConfig emailConfig) throws Exception{
		EmailConfig config = emailConfig.compare(this.emailConfigService.getEmailConfig());
		if(config.isHasChange()){
			this.emailConfigService.saveEmailConfig(config);
		}
		return getSuccessMap("Save Success!");
	}
	
}
