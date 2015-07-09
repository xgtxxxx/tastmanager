package task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import task.dao.RoleMapper;
import task.model.Role;
import task.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	@CacheEvict(value="role",allEntries=true)
	public void addUserRole(Integer roleId, Integer userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		this.roleMapper.addUserRole(params);
	}
	
	@Override
	@CacheEvict(value="role",allEntries=true)
	public void delUserRole(Integer roleId, Integer userId) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		this.roleMapper.delUserRole(params);
	}

	@Override
	@Cacheable(value="role")
	public List<Role> getAll(Map<String, Object> whereMap) {
		return roleMapper.selectAll(whereMap);
	}

	@Override
	@Cacheable(value="role")
	public Role selectByPrimaryKey(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	@CacheEvict(value="role",allEntries=true)
	public void insertRoleSelective(Role role) {
		roleMapper.insertSelective(role);
	}

	@Override
	@CacheEvict(value="role",allEntries=true)
	public void updateRoleSelective(Role role) {
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	@CacheEvict(value="role",allEntries=true)
	public void deleteRoleById(int id) {
		roleMapper.deleteByPrimaryKey(id);
		roleMapper.deleteRoleUser(id);
	}

	@Override
	public List<Role> listMyRoles(Integer userId) {
		return roleMapper.listMyRoles(userId);
	}
	
	@Override
	public List<Role> listOtherRoles(Integer userId) {
		return roleMapper.listOtherRoles(userId);
	}

}
