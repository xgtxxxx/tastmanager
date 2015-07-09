package task.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import task.base.BaseController;
import task.model.Team;
import task.service.TeamService;
import task.service.UserService;
import task.util.StringUtil;

/**
 * @author Kiven
 * @date Jul 25, 2014 2:07:30 PM
 */
@Controller
@RequestMapping(value="/team")
public class TeamController extends BaseController {
	@Autowired
	private TeamService teamService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getAll",method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTeam(String name,String isOwn) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		if(!StringUtil.isEmpty(isOwn)){
			whereMap.put("userId", this.getCurrentUser().getId());
		}
		
		if (!StringUtil.isEmpty(name)) {
			whereMap.put("name", name);
		}
		
		map.put("recordlist", teamService.getAll(whereMap));
		
		return map;
	}
	
	@RequestMapping(value="/saveTeam",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addTeam(Team team) {
		
    	if(team.getId()==null){
    		teamService.insertTeamSelective(team);
    		return getMessageMap(true, "Add team success!");
        } else {
            teamService.updateTeamSelective(team);
        	return getMessageMap(true, "Add team success!");
        }
	}
	
	@RequestMapping(value="/deleteTeam")
	public @ResponseBody Map<String, Object> deleteTeam(Integer id) {
		teamService.deleteTeamById(id);
		return getMessageMap(true, "Delete team success!");
	}
	
	@RequestMapping(value="/listMyTeams", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listMyTeams(Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recordlist", this.teamService.listMyTeams(userId));
		
		return map;
	}
	@RequestMapping(value="/listOtherTeams", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listOtherTeams(Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recordlist", this.teamService.listOtherTeams(userId));
		
		return map;
	}
	
	@RequestMapping(value="/doSetTeam")
	public @ResponseBody Map<String, Object> doSetTeam(Integer teamId, 
			Integer userId,String flag) {
		if("add".equals(flag)){
			this.teamService.addUserTeam(teamId,userId);
		}else if("del".equals(flag)){
			this.teamService.delUserTeam(teamId,userId);
		}
		return getMessageMap(true, "Do success!");
	}
}
