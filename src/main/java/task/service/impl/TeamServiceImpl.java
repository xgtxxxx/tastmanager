package task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import task.base.BaseService;
import task.dao.TeamMapper;
import task.model.Team;
import task.service.TeamService;

/**
 * @author Kiven
 * @date Jul 25, 2014 1:39:10 PM
 */
@Service
public class TeamServiceImpl extends BaseService implements TeamService {
	@Autowired
	private TeamMapper teamMapper;
	
	@Cacheable(value="team")	
	public List<Team> getAll(Map<String, Object> whereMap) {
		return teamMapper.selectAll(whereMap);
	}

	@Cacheable(value="team")
	public List<Team> getTaskTeams(Map<String, Object> whereMap) {
		return teamMapper.selectTaskTeams(whereMap);
	}

	@Cacheable(value="team")
	public List<Team> getTLTeams(Map<String, Object> whereMap) {
		return teamMapper.selectTLTeams(whereMap);
	}

	@CacheEvict(value="team",allEntries=true)
	public void insertTeamSelective(Team team) {
		teamMapper.insertSelective(team);
	}

	@CacheEvict(value="team",allEntries=true)
	public void updateTeamSelective(Team team) {
		teamMapper.updateByPrimaryKeySelective(team);
	}

	@CacheEvict(value="team",allEntries=true)
	public void deleteTeamById(int id) {
		teamMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Cacheable(value="team")
	public List<Team> listMyTeams(Integer userId) {
		return teamMapper.listMyTeams(userId);
	}

	@Override
	@Cacheable(value="team")
	public List<Team> listOtherTeams(Integer userId) {
		return teamMapper.listOtherTeams(userId);
	}

	@Override
	@CacheEvict(value="team",allEntries=true)
	public void addUserTeam(Integer teamId, Integer userId) {
		Map<String,Integer> p = new HashMap<String, Integer>();
		p.put("userId", userId);
		p.put("teamId", teamId);
		teamMapper.addUserTeam(p);
	}
	
	@Override
	@CacheEvict(value="team",allEntries=true)
	public void delUserTeam(Integer teamId, Integer userId) {
		Map<String,Integer> p = new HashMap<String, Integer>();
		p.put("userId", userId);
		p.put("teamId", teamId);
		teamMapper.delUserTeam(p);
	}

}
