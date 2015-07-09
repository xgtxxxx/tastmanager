package task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task.base.BaseService;
import task.dao.UserMapper;
import task.model.User;
import task.service.UserService;

@Service
public class UserServiceImpl extends BaseService implements UserService {

	@Override
	public void addUserRole(Integer userId, Integer roleId) {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("userId", userId);
		m.put("roleId", roleId);
		this.userMapper.addUserRole(m);
	}

	@Override
	public void addUserTeam(Integer userId, Integer teamId) {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("userId", userId);
		m.put("teamId", teamId);
		this.userMapper.addUserTeam(m);
	}

	@Override
	public void delUserRole(Integer userId, Integer roleId) {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("userId", userId);
		m.put("roleId", roleId);
		this.userMapper.delUserRole(m);
	}

	@Override
	public void delUserTeam(Integer userId, Integer teamId) {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("userId", userId);
		m.put("teamId", teamId);
		this.userMapper.delUserTeam(m);
	}

	@Override
	public List<User> listOtherUserByRole(Integer roleId) {
		return userMapper.listOtherUserByRole(roleId);
	}

	@Override
	public List<User> listMyUserByRole(Integer roleId) {
		return userMapper.listMyUserByRole(roleId);
	}
	
	@Override
	public List<User> listMyUserByTeam(Integer teamId) {
		return userMapper.listMyUserByTeam(teamId);
	}

	@Override
	public List<User> listOtherUserByTeam(Integer teamId) {
		return userMapper.listOtherUserByTeam(teamId);
	}

	@Autowired
	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public User getUserByAccount(String account) {
		return userMapper.selectByAccount(account);
	}

	public User getUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public List<User> getAll(Map<String, Object> whereMap) {
		return userMapper.selectAll(whereMap);
	}

	public void insertUserSelective(User user) {
		userMapper.insertSelective(user);
		this.addUserRole(user.getId(), 2);
	}

	public void updateUserSelective(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	public void deleteUserById(int id) {
		userMapper.deleteByPrimaryKey(id);
		userMapper.deleteTeamUser(id);
		userMapper.deleteRoleUser(id);
	}

	public User checkUserByAccountAndId(User user) {
		return userMapper.selectByAccountAndId(user);
	}
}
