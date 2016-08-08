package com.onebrain.workspace;

import java.io.Serializable;

import javax.persistence.*;

import com.onebrain.user.User;

/**
 * Entity implementation class for Entity: Workspace
 *
 */
@Entity

@NamedQuery(query = "SELECT w FROM Workspace w WHERE w.accesslist.owner = :owner", name = "find workspaces by user")
public class Workspace implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id 
	private int id;
	
	@Column(columnDefinition="varchar(32)", unique=true)
	private String name;
	
	@Column(columnDefinition="varchar(128)")
	private String description;
	
	@OneToOne
	AccessList accesslist;

	public Workspace() {
		super();
	}

	public Workspace(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccessList getAccesslist() {
		return accesslist;
	}

	public void setAccesslist(AccessList accesslist) {
		this.accesslist = accesslist;
	}
	
	public void initAccessList(User owner){
		this.accesslist = new AccessList(owner);
	}

	@Override
	public String toString() {
		return "Workspace [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
   
}
