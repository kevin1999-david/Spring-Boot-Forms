package com.bolsaideas.springboot.form.app.models.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


import com.bolsaideas.springboot.form.app.validation.RegexIdentifier;
import com.bolsaideas.springboot.form.app.validation.Required;

public class User {

	// @Pattern(regexp = "[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][a-zA-Z]{1}")
	@RegexIdentifier
	private String identifier;
	// @NotEmpty(message = "The name can't be empty")
	private String name;
	@Required
	private String lastname;
	@NotBlank
	@Size(min = 3, max = 8)
	private String username;
	@NotEmpty
	private String userpass;
	@NotEmpty(message = "The email is not in the correct format")
	@Email
	private String useremail;
	@NotNull // for objects
	@Min(5)
	@Max(5000)
	private Integer account;

	@NotNull
	@Past // @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	@NotNull
	private Country country;
	
	@NotEmpty 
	private List<Role> roles;
	
	private Boolean enable;
	
	@NotEmpty
	private String gender;
	
	private String secretValue;

	public User(String username, String userpass, String useremail) {
		this.username = username;
		this.userpass = userpass;
		this.useremail = useremail;
	}

	public User() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSecretValue() {
		return secretValue;
	}

	public void setSecretValue(String secretValue) {
		this.secretValue = secretValue;
	}


	

		
}
