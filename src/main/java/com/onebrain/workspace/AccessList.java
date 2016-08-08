package com.onebrain.workspace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.onebrain.user.User;

/**
 * Entity implementation class for Entity: AccessList
 *
 */

@Entity
public class AccessList implements Serializable {

	
	private static final long serialVersionUID = 1L; 
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	
	@Column
	private User owner;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<User> members;
	
	public AccessList() {
		super();
	}
	
	public AccessList(User owner){
		this.owner = owner;
		this.members = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}
	
	public void addMember(User member){
		members.add(member);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AccessList [id=" + id + ", Owner=" + owner + ", members=" + members + "]";
	}
   
}
