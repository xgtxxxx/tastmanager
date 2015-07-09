package task.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import task.model.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring*.xml"})
public class TestUserService {
	private UserService userService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Test
	public void test() {
		User user = userService.getUserByAccount("shihai");
		try {
			System.out.println(objectMapper.writeValueAsString(user));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
