package task.architecture.security;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetail extends User {
	private static final long serialVersionUID = 1762647702861905200L;
	protected Logger logger = Logger.getLogger(this.getClass());
	private task.model.User original;
	
	public CustomUserDetail(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		logger.info("Initialized UserDetail bean...");
	}

	public task.model.User getOriginal() {
		return original;
	}

	public void setOriginal(task.model.User original) {
		this.original = original;
	}
	

}
