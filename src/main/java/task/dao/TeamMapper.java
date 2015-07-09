package task.dao;

import java.util.List;
import java.util.Map;

import task.model.Team;
@SuppressWarnings("rawtypes")
public interface TeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);
    
    List<Team> selectAll(Map<String, Object> whereMap);
    
    List<Team> selectTaskTeams(Map<String, Object> whereMap);
    
    List<Team> selectTLTeams(Map<String, Object> whereMap);

    List<Team> listMyTeams(Integer userId);

    List<Team> listOtherTeams(Integer userId);

	void addUserTeam(Map p);

	void delUserTeam(Map p);
	
	List<Team> selectTeamForNotice();
}