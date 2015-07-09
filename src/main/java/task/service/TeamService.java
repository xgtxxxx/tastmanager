package task.service;

import java.util.List;
import java.util.Map;

import task.model.Team;

/**
 * @author Kiven
 * @date Jul 24, 2014 3:30:24 PM
 */
public interface TeamService {
	List<Team> getAll(Map<String, Object> whereMap);
	
	List<Team> getTaskTeams(Map<String, Object> whereMap);
	
	List<Team> getTLTeams(Map<String, Object> whereMap);
	
	void insertTeamSelective(Team team);
	
	void updateTeamSelective(Team team);
	
	void deleteTeamById(int id);

	List<Team> listMyTeams(Integer userId);

	List<Team> listOtherTeams(Integer userId);

	void addUserTeam(Integer teamId, Integer userId);

	void delUserTeam(Integer teamId, Integer userId);
}
