package co.startupweek.foundroom.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService implements UserDetailsService {

	HashMap<String, User> users;

	public UserService() {
		users = new HashMap<>();
	}

	public User generateAnonymous() {
		User anonymous = new User();

		users.put(anonymous.getUsername(), anonymous);

		return anonymous;
	}

	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		if (users.containsKey(username))
			return users.get(username);
		else
			throw new UsernameNotFoundException("No such user");
	}

}
