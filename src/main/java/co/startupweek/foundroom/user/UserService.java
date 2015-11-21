package co.startupweek.foundroom.user;

import co.startupweek.foundroom.login.MigrateDTO;
import javassist.tools.web.BadHttpRequest;
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

	public User migrateUser(MigrateDTO migrate) {
		if (users.containsKey(migrate.getOldUsername())) {
			if (users.get(migrate.getOldUsername()).getPassword().equals(migrate.getOldPassword())) {
				if (users.containsKey(migrate.getNewUsername())) {
					throw new IllegalArgumentException("The new username is taken");
				}
				else {
					users.put(migrate.getNewUsername(), new User(users.get(migrate.getOldUsername()), migrate.getNewUsername(), migrate.getNewPassword(), migrate.getTwitter()));
					users.remove(migrate.getOldUsername());
				}
			}
			else {
				throw new IllegalArgumentException("Old password is incorrect");
			}
		}
		else {
			throw new IllegalArgumentException("Old username not found");
		}

		return users.get(migrate.getNewUsername());
	}

	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		if (users.containsKey(username))
			return users.get(username);
		else
			throw new UsernameNotFoundException("No such user");
	}

}
