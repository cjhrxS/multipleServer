package ua.ita.smartcarservice.dto;

import java.util.Set;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpForm {
	
	@NotBlank
	@Size(min = 2, max = 10)
	private String username;
	
	@NotBlank
	@Size(min = 8, max = 24)
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	@Size(min = 2, max = 30)
	private String fullName;
	@NotBlank
	private String numberPhone;
	
	private Set<String> role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	


}
