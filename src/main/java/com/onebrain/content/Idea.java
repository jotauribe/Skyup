package com.onebrain.content;

import java.io.Serializable;
import javax.persistence.*;

import com.onebrain.user.User;
import com.onebrain.workspace.Workspace;

/**
 * Entity implementation class for Entity: Idea
 *
 */
@Entity

public class Idea implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@ManyToOne
	private User author;
	
	@ManyToOne
	private Workspace workspace;
	

	public Idea() {
		super();
	}

	public Idea(String title, String description, User author, Workspace workspace){
		this.title = title;
		this.description = description;
		this.author = author;
		this.workspace = workspace;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	@Override
	public String toString() {
		return "Idea [id=" + id + ", title=" + title + ", description=" + description + ", author=" + author + "]";
	}
   
}
