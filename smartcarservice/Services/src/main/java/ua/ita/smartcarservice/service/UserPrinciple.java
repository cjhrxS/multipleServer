package ua.ita.smartcarservice.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ua.ita.smartcarservice.entity.UserEntity;

/**
 * 
 * UserPrinciple is not used directly by Spring Security for security purposes.
 * It simply stores user information which is later encapsulated into
 * Authentication objects. This allows non-security related user information
 * (such as email addresses, telephone numbers etc) to be stored.
 *
 */

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 1574386194951783012L;

	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple() {

	}

	public UserPrinciple(Long id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrinciple build(UserEntity userEntity) {
		List<GrantedAuthority> authorities = userEntity.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserPrinciple(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), authorities);
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		UserPrinciple user = (UserPrinciple) obj;
		return Objects.equals(id, user.id);
	}

}
