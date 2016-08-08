package com.onebrain.workspace;

import java.util.List;

import com.onebrain.user.User;

public interface WorkspaceManagerInterface {

	public Workspace getById(int workspaceId);
	public void addWorkspace(Workspace workspace, User owner);
	public void addMember(User user, int workspaceId);
	public List<Workspace> getWorkspacesByUserID(User user);
	public Workspace getByName(String name);
		
}
