package co.startupweek.foundroom.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;

public class User implements UserDetails {

	@NotNull
	private String username;

	@NotNull
	private String password;

	private String twitter;

	private int chatroom;

	@NotNull
	private int rating;

	public User() {
		SecureRandom random = new SecureRandom();
		username = new BigInteger(130, random).toString(32);
		password = new BigInteger(130, random).toString(32);
		chatroom = -1;
	}

	public User(User user, String newUsername, String newPassword, String twitter) {
		this.username = newUsername;
		this.password = newPassword;
		this.twitter = twitter;
		this.rating = user.rating;
		chatroom = -1;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("ROLE_USER"));
		return list;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public int getChatroom() {
		return chatroom;
	}

	public void setChatroom(int chatroom) {
		this.chatroom = chatroom;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (!username.equals(user.username)) return false;
		return password.equals(user.password);

	}

	@Override
	public int hashCode() {
		int result = username.hashCode();
		result = 31 * result + password.hashCode();
		return result;
	}
}
