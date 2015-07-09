package task.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import task.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByAccount(String account);
    
    User selectByAccountAndId(User record);
    
    List<User> selectAll(Map<String, Object> whereMap);

	void deleteTeamUser(int id);

	void deleteRoleUser(int id);

	List<User> listOtherUserByRole(Integer roleId);

	List<User> listMyUserByRole(Integer roleId);

	@SuppressWarnings("rawtypes")
	void addUserRole(Map m);
	@SuppressWarnings("rawtypes")
	void addUserTeam(Map m);
	@SuppressWarnings("rawtypes")
	void delUserRole(Map m);
	@SuppressWarnings("rawtypes")
	void delUserTeam(Map m);

	List<User> listMyUserByTeam(Integer teamId);

	List<User> listOtherUserByTeam(Integer teamId);
	
	List<User> selectNoTaskByTeam(Integer teamId);
	
	List<User> selectByTeams(Set<Integer> tids);
}