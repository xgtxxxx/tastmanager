package task.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import task.model.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/*.xml"})
public class TestStatus {
	private StatusService statusService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}
	
	@Test
	public void test() {
		try {
			List<Status> ss = statusService.getAll();
			System.out.println(objectMapper.writeValueAsString(ss));
			
			Status s = ss.get(0);
			s.setDescription(""+new Date());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		try {
			List<Status> ss2 = statusService.getAll();
			System.out.println(objectMapper.writeValueAsString(ss2));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		try {
			List<Status> ss3 = statusService.getAll();
			System.out.println(objectMapper.writeValueAsString(ss3));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
