package task.architecture.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import task.model.Role;
import task.model.User;

@Component
public class AuthenticationHelper {
	@Autowired
	private Md5PasswordEncoder md5Encoder;

	public List<GrantedAuthority> getAuthList(Collection<Role> roles) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
			authList.add(new SimpleGrantedAuthority("ROLE_" + getRoleName(role)));
		}

		return authList;
	}

	private String getRoleName(Role role) {
		return StringUtils.upperCase(role.getName());
	}

	public List<GrantedAuthority> getAuthList(String... roles) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

		for (int i = 0; i < roles.length; i++) {
			String role = roles[i];
			authList.add(new SimpleGrantedAuthority("ROLE_" + StringUtils.upperCase(role)));
		}

		return authList;
	}

	public void setCurrentUser(User user) {
		List<Role> roles = user.getRoles();
		List<GrantedAuthority> authList = getAuthList(roles);

		String encodedPwd = md5Encoder.encodePassword(user.getPassword(), null); // TODO: to add salt later
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getAccount(), encodedPwd, authList);
		SecurityContext ctx = SecurityContextHolder.getContext();
		ctx.setAuthentication(token);
	}

	public Authentication getCurrentAuthentication() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx == null) {
			return null;
		} else {
			return ctx.getAuthentication();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean hasRole(String role) {
		Authentication auth = getCurrentAuthentication();
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) auth.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equalsIgnoreCase(role)) {
				return true;
			}
		}

		return false;
	}

	public UserDetails getCurrentAuthenticatedUser() {
		Authentication auth = this.getCurrentAuthentication();
		if (auth != null) {
			return (UserDetails) auth.getPrincipal();
		} else {
			return null;
		}
	}
}
