package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ST_USER")
public class UserDTO extends BaseDTO{

	@Column(name = "FIRSTNAME", length = 50)
	private String firstName;

	@Column(name = "LASTNAME", length = 50)
	private String lastName;

	@Column(name = "LOGINID", length = 50)
	private String loginId;

	@Column(name = "PASSWORD", length = 50)
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
