package task.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import task.base.BaseController;
import task.model.Status;
import task.service.StatusService;

/**
 * @author Kiven
 * @date Jul 25, 2014 1:48:14 PM
 */
@Controller
@RequestMapping(value = "/status")
public class StatusController extends BaseController {
	@Autowired
	private StatusService statusService;

	@RequestMapping(value = "/listStatus", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getStatus() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("recordlist", statusService.getAll());

		return map;
	}
	
	@RequestMapping(value="/deleteStatus", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteStatus(Integer id) {
		statusService.deleteStatusById(id);
		
		return getMessageMap(true, "Delete user success!");
	}
	
	@RequestMapping(value="/saveStatus",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveStatus(Status status) {
		if(status.getId()==null){
			statusService.insertStatus(status);
			return getMessageMap(true, "save status success!");
		}else{
			statusService.updateStatus(status);
			return getMessageMap(true, "update status success!");
		}
		
	}
}
