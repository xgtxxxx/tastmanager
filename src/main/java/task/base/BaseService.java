package task.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import task.architecture.security.CustomUserDetail;

/**
 * Base service class
 */
public abstract class BaseService {
	protected Logger logger = Logger.getLogger(this.getClass());

	public Map<String, Object> queryForPagerJsonString(List<?> list, int count) {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("recordlist", list);
		data.put("totalcount", count);

		return data;
	}
	
	/**
	 * get current authenticated user detail
	 * 
	 * @return current authenticated user detail
	 */
	protected UserDetails getCurrentAuthenticatedUser() {
		SecurityContext holder = SecurityContextHolder.getContext();
		Authentication auth = holder.getAuthentication();
		return (CustomUserDetail) auth.getPrincipal();
	}
}
