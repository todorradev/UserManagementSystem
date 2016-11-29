package com.toshko.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserDTO {
	private Long id;

	@Size(max = 50)
	private String firstName;

	@Size(max = 50)
	private String lastName;

	@Size(max = 50)
	private String password;

	@Email
	@Size(min = 5, max = 100)
	private String email;

	private String birthdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	@Override
	public String toString() {
		return "User{" + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
			+ '\'' + ", email='" + email + '\'' + "}";
	}
}
