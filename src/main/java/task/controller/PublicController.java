package task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import task.base.BaseController;

/**
 * @author Kiven
 * @date Jul 24, 2014 3:29:38 PM
 */
@Controller
public class PublicController extends BaseController {
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String handleLogin(@RequestParam(value="error", required=false) Boolean error, ModelMap modelMap) {
		if (error == null || !error) {
			modelMap.put("errors", "");
		} else {
			modelMap.put("errors", "The account or password is wrong");
		}
		logger.debug("login " + error);
		return "login/login";
	}
	
	@RequestMapping(value="/failed", method = RequestMethod.GET)
	public String handleLoginFaild() {
		logger.debug("login failed");
		return "failed";
	}
	
	@RequestMapping(value="/loginsuccess", method = RequestMethod.GET)
	public String handleLoginSuccess() {
		return "redirect:/task/main";
	}
}
