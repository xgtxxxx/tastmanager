package task.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import task.base.BaseController;
import task.mail.MailService;
import task.model.User;
import task.service.UserService;
import task.util.StringUtil;

/**
 * @author Kiven
 * @date Jul 24, 2014 3:29:38 PM
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private Md5PasswordEncoder passwordEncoder;
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value="/getUser", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recordlist", userService.getAll(map));
		
		return map;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getUser(String account) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		if (!StringUtil.isEmpty(account)) {
			whereMap.put("account", account);
		}
		
		map.put("recordlist", userService.getAll(whereMap));
		
		return map;
	}
	
	@RequestMapping(value="/saveUser",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addUser(User user) {
		if(user.getId()==null){
			//check whether the account is exist
			User temp = userService.getUserByAccount(user.getAccount());
			if (temp != null) {
				return getMessageMap(false, "Account is exist, please change!");
			}
			long psw=Math.round(Math.random()*8999+1000);
			user.setPassword(passwordEncoder.encodePassword(String.valueOf(psw), user.getAccount()));
			try{
				this.mailService.sendUserInfo(user.getAccount(),psw,"Hello New User");
				userService.insertUserSelective(user);
			}catch(Exception e){
				return getFailureMap("You account is not a valid email!");
			}
		}else{
			userService.updateUserSelective(user);
		}
		return getMessageMap(true, "success!");
	}
	
	@RequestMapping(value="/resetPassword")
	public @ResponseBody Map<String, Object> resetPassword(Integer userId) {
		User temp = userService.getUserById(userId);
		if (temp == null) {
			return getMessageMap(false, "Account is not exist!");
		}
		long psw=Math.round(Math.random()*8999+1000);
		temp.setPassword(passwordEncoder.encodePassword(String.valueOf(psw), temp.getAccount()));
		try{
			this.mailService.sendUserInfo(temp.getAccount(),psw,"Reset Password Success");
			this.userService.updateUserSelective(temp);
			return getMessageMap(true, "success!");
		}catch(Exception e){
			return getFailureMap("You account is not a valid email!");
		}
	}

	@RequestMapping(value="/deleteUser", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteUser(Integer id) {
		userService.deleteUserById(id);
		
		return getMessageMap(true, "Delete user success!");
	}
	
	@RequestMapping(value="/listOtherUserByRole", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listOtherUserByRole(Integer roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recordlist", this.userService.listOtherUserByRole(roleId));
		
		return map;
	}
	
	@RequestMapping(value="/listMyUserByRole", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listMyUserByRole(Integer roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recordlist", this.userService.listMyUserByRole(roleId));
		
		return map;
	}
	
	@RequestMapping(value="/listOtherUserByTeam", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listOtherUserByTeam(Integer teamId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recordlist", this.userService.listOtherUserByTeam(teamId));
		
		return map;
	}
	
	@RequestMapping(value="/listMyUserByTeam", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listMyUserByTeam(Integer teamId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recordlist", this.userService.listMyUserByTeam(teamId));
		
		return map;
	}
	
	@RequestMapping(value="/doSetUser")
	public @ResponseBody Map<String, Object> doSetRole(Integer userId,Integer teamId,Integer roleId,String flag,String type) {
		if("add".equals(flag)){
			if("role".equals(type)){
				this.userService.addUserRole(userId,roleId);
			}
			if("team".equals(type)){
				this.userService.addUserTeam(userId,teamId);
			}
		}else if("del".equals(flag)){
			if("role".equals(type)){
				this.userService.delUserRole(userId,roleId);
			}
			if("team".equals(type)){
				this.userService.delUserTeam(userId,teamId);
			}
		}
		return getMessageMap(true, "Do success!");
	}
	
	@RequestMapping(value="/changePassword",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> changePassword(String password,String password1) {
		User u = this.getCurrentUser();
		String psw1 = passwordEncoder.encodePassword(password, u.getAccount());
		if(psw1.equals(u.getPassword())){
			User newu = this.userService.getUserById(u.getId());
			newu.setPassword(passwordEncoder.encodePassword(password1, u.getAccount()));
			u.setPassword(newu.getPassword());
			this.userService.updateUserSelective(newu);
			return getSuccessMap("Change success!");
		}else{
			return getFailureMap("Old password is wrong!");
		}
	}
	
	@RequestMapping(value="/checkPassword")
	public @ResponseBody Map<String, Object> checkPassword(String password) {
		User u = this.getCurrentUser();
		String psw1 = passwordEncoder.encodePassword(password, u.getAccount());
		if(psw1.equals(u.getPassword())){
			return getSuccessMap("success");
		}else{
			return getFailureMap("failure");
		}
	}
	
}
