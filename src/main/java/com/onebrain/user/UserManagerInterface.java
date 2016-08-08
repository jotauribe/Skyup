package com.onebrain.user;

public interface UserManagerInterface {
	
	public void insert();
	
	public User createUser(User user);
	public User getById(int id);
	public User validateUser(String name, String password);

}
