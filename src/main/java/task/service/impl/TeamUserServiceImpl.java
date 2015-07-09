package task.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task.dao.TeamUserMapper;
import task.model.TeamUser;
import task.service.TeamUserService;

/**
 * @author Kiven
 * @date Sep 10, 2014 2:20:41 PM
 */
@Service("teamUserService")
public class TeamUserServiceImpl implements TeamUserService{
	
	@Autowired
	private TeamUserMapper teamUserMapper;
	
	public List<TeamUser> getAll(Map<String, Object> whereMap) {
		return teamUserMapper.selectTeamUser(whereMap);
	}

	public void insertTeamUserSelective(TeamUser teamUser) {
		teamUserMapper.insertSelective(teamUser);
	}

	public void deleteTeamUser(int id) {
		teamUserMapper.deleteByPrimaryKey(id);
	}

	public void updateTeamUserSelective(TeamUser teamUser) {
		teamUserMapper.updateByPrimaryKeySelective(teamUser);
	}

}
