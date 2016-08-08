package com.onebrain.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(query="SELECT u FROM User u WHERE u.name = :userName AND u.password = :userPassword", name="userValidationQuery")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name", columnDefinition="varchar(16)")
	private String name;
	
	@Column(name="email", columnDefinition="varchar(32)")
	private String email;
	
	@Column(name="password", columnDefinition="varchar(16)")
	private String password;
	
	public User(){}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	

}
