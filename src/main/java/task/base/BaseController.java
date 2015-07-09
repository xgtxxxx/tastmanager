package task.base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;

import task.architecture.security.AuthenticationHelper;
import task.architecture.security.CustomUserDetail;
import task.model.User;
import task.model.vo.Header;
import task.model.vo.Javascript;
import task.service.UserService;
import task.util.DateUtil;

/**
 * Base controller class
 * 
 * @author Kiven
 * @date Jul 24, 2014 3:29:50 PM
 */
public abstract class BaseController {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected AuthenticationHelper authHelper;
	@Autowired
	private UserService userService;
	/**
	 * get success data for javascript response
	 * 
	 * @param success
	 * boolean
	 * @param message
	 * any messages
	 * @return structured response map
	 */
	protected Map<String, Object> getMessageMap(boolean success, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", success);
		map.put("message", message);

		return map;
	}

	protected Map<String, Object> getSuccessMap(String message, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("message", message);
		map.put("data", data);

		return map;
	}
	
	protected Map<String, Object> getSuccessMap(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("message", message);

		return map;
	}

	protected Map<String, Object> getFailureMap(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		map.put("message", message);

		return map;
	}

	/**
	 * get current authenticated user detail
	 * 
	 * @return current authenticated user detail
	 */
	protected CustomUserDetail getCurrentAuthenticatedUser() {
		return (CustomUserDetail) authHelper.getCurrentAuthenticatedUser();
	}

	/**
	 * get current authentication
	 * 
	 * @return authentication
	 */
	protected Authentication getCurrentAuthentication() {
		return authHelper.getCurrentAuthentication();
	}

	@ModelAttribute("headers")
	protected Header getHeader() {
		Header header = new Header();
		Set<Javascript> js = header.getJs();
		Javascript logout = new Javascript("logout");
		Javascript authenticate = new Javascript("authentication");

		js.add(logout);
		js.add(authenticate);
		return header;
	}


	protected String getCurrentDatetimeString() {
		return DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	protected User getCurrentUser(){
		return this.getCurrentAuthenticatedUser().getOriginal();
	}
}
