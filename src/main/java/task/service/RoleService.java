package task.service;

import java.util.List;
import java.util.Map;

import task.model.Role;

public interface RoleService {
	List<Role> getAll(Map<String, Object> whereMap);
	
	Role selectByPrimaryKey(Integer id);
	
	void insertRoleSelective(Role role);
	
	void updateRoleSelective(Role role);
	
	void deleteRoleById(int id);

	List<Role> listMyRoles(Integer userId);
	
	List<Role> listOtherRoles(Integer userId);

	void addUserRole(Integer roleId, Integer userId);

	void delUserRole(Integer roleId, Integer userId);
}