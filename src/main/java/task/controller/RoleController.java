package task.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import task.base.BaseController;
import task.model.Role;
import task.service.RoleService;
import task.util.StringUtil;

/**
 * @author Kiven
 * @date Jul 24, 2014 3:29:38 PM
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getRole(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		if (!StringUtil.isEmpty(name)) {
			whereMap.put("name", name);
		}
		
		map.put("recordlist", roleService.getAll(whereMap));
		
		return map;
	}
	
	@RequestMapping(value="/getRole", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllRole() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recordlist", roleService.getAll(new HashMap<String, Object>()));
		
		return map;
	}

	@RequestMapping(value="/saveRole",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addRole(Role role) {
		
		if(role.getId()==null){
    		roleService.insertRoleSelective(role);
    		return getMessageMap(true, "Add role success!");
        } else {
        	roleService.updateRoleSelective(role);
        	return getMessageMap(true, "update role success!");
        }
	}
	

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> deleteRole(ModelMap modelMap, 
														@PathVariable("id") int id) {
		roleService.deleteRoleById(id);
		
		return getMessageMap(true, "Delete role success!");
	}
	
	@RequestMapping(value="/listMyRoles", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listMyRoles(Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recordlist", this.roleService.listMyRoles(userId));
		
		return map;
	}
	@RequestMapping(value="/listOtherRoles", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listOtherRoles(Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recordlist", this.roleService.listOtherRoles(userId));
		
		return map;
	}
	
	@RequestMapping(value="/doSetRole")
	public @ResponseBody Map<String, Object> doSetRole(Integer roleId, 
			Integer userId,String flag) {
		if("add".equals(flag)){
			this.roleService.addUserRole(roleId,userId);
		}else if("del".equals(flag)){
			this.roleService.delUserRole(roleId,userId);
		}
		return getMessageMap(true, "Do success!");
	}
	
	@RequestMapping(value="/deleteRole", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteRole(Integer id) {
		roleService.deleteRoleById(id);
		
		return getMessageMap(true, "Delete user success!");
	}
	
}
