package task.service;

import java.util.List;
import java.util.Map;

import task.model.TeamUser;

/**
 * @author Kiven
 * @date Sep 10, 2014 1:31:30 PM
 */
public interface TeamUserService {
	List<TeamUser> getAll(Map<String, Object> whereMap);
	
	void insertTeamUserSelective(TeamUser teamUser);
	
	void updateTeamUserSelective(TeamUser teamUser);
	
	void deleteTeamUser(int id);
}
