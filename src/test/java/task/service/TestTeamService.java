package task.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring*.xml"})
public class TestTeamService {
	private TeamService teamService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	public void setTeamService(TeamService teamService){
		this.teamService = teamService;
	}
	
	@Test
	public void test(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 0);
		map.put("limit", 2);
		
		try {
			System.out.println(objectMapper.writeValueAsString(teamService.getTaskTeams(map)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
