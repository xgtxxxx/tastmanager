package task.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import task.base.BaseController;
import task.model.ReleaseVersion;
import task.service.ReleaseVersionService;

/**
 * @date Jul 25, 2014 1:48:14 PM
 */
@Controller
@RequestMapping(value = "/version")
public class ReleaseVersionController extends BaseController {
	@Autowired
	private ReleaseVersionService releaseVersionService;

	@RequestMapping(value = "/listVersion", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listVersion() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("recordlist", releaseVersionService.getAll());

		return map;
	}
	
	@RequestMapping(value = "/getActiveVersion", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getActiveVersion(String query) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recordlist", releaseVersionService.listActiveVersion(query));

		return map;
	}
	
	@RequestMapping(value="/deleteVersion", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteVersion(Integer id) {
		releaseVersionService.deleteById(id);
		
		return getMessageMap(true, "Delete user success!");
	}
	
	@RequestMapping(value="/saveReleaseVersion",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveVersion(ReleaseVersion version) {
		if(version.getId()==0){
			releaseVersionService.insertVersion(version);
			return getMessageMap(true, "Save status success!");
		}else{
			releaseVersionService.updateVersion(version);
			return getMessageMap(true, "Update status success!");
		}
		
	}
}
