package com.example.dto;

import java.util.Collection;
import java.util.Set;

public class UserDto {

	private Long id;
	private String username;
	private String password;
	private String name;
    private String passwordConfirm;
    private Collection<? extends Long> roles;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public Collection<? extends Long> getRoles() {
		return roles;
	}
	public void setRoles(Collection<? extends Long> roles) {
		this.roles = roles;
	}
}
