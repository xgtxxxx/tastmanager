package task.dao;

import java.util.List;
import java.util.Map;

import task.model.Role;
@SuppressWarnings("rawtypes")
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectAll(Map<String, Object> whereMap);

	List<Role> listMyRoles(Integer userId);
	
	List<Role> listOtherRoles(Integer userId);

	void addUserRole(Map params);

	void delUserRole(Map params);

	void deleteRoleUser(int id);
}