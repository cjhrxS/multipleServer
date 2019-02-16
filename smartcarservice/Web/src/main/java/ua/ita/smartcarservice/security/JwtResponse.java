package ua.ita.smartcarservice.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;


/**
 * 
 * This class will be returned by SpringBoot server once an authentication is successful, it contains:
 * 1. JWT Token
 * 2. Schema Type of Token
 * 3. Username
 * 4. Array of User’s Authorities
 *
 */


public class JwtResponse {
	
	private String token;
	private String type;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = accessToken;
		this.type = "Bearer ";
		this.username = username;
		this.authorities = authorities;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	
}
