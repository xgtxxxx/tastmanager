package task.architecture.security;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import task.model.User;
import task.service.UserService;

public class CustomUserDetailService implements UserDetailsService {

	private Logger logger = Logger.getLogger(CustomUserDetailService.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationHelper authHelper;

	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		logger.info("Loading user's authentication pricipal and credentials. account : "+account);
		CustomUserDetail userDetails = null;
		User user = userService.getUserByAccount(account);

		if (user == null) {
			throw new UsernameNotFoundException(account);
		}

		List<GrantedAuthority> roles = authHelper.getAuthList(user.getRoles());
		
		userDetails = new CustomUserDetail(user.getAccount(), user.getPassword(), user.getEnabled(), user.getExpired(), true, true, roles);
		
		userDetails.setOriginal(user);

		return userDetails;
	}
	
}
