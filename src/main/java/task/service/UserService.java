package task.service;

import java.util.List;
import java.util.Map;

import task.model.User;

/**
 * @author Kiven
 * @date Jul 24, 2014 3:30:24 PM
 */
public interface UserService {
	User getUserByAccount(String account);
	
	User getUserById(int id);
	
	List<User> getAll(Map<String, Object> whereMap);
	
	void insertUserSelective(User user);
	
	void updateUserSelective(User user);
	
	void deleteUserById(int id);
	
	User checkUserByAccountAndId(User user);

	List<User> listOtherUserByRole(Integer roleId);

	List<User> listMyUserByRole(Integer roleId);

	void addUserRole(Integer userId, Integer roleId);

	void addUserTeam(Integer userId, Integer teamId);

	void delUserRole(Integer userId, Integer roleId);

	void delUserTeam(Integer userId, Integer teamId);

	List<User> listMyUserByTeam(Integer teamId);

	List<User> listOtherUserByTeam(Integer teamId);
}
