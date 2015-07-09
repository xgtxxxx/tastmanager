package task.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring*.xml"})
public class TestRoleService {
	private RoleService roleService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Test
	public void Test(){
		 try {
			System.out.println(objectMapper.writeValueAsString(roleService.selectByPrimaryKey(1)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		 //System.out.println(JSONObject.toJSONString(roleService.getAll()));
	}
	
}

