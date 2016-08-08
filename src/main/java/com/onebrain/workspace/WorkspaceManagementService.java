package com.onebrain.workspace;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.onebrain.user.User;

@Stateless
public class WorkspaceManagementService implements WorkspaceManagerInterface {
	
	@PersistenceContext(unitName="MySQLOnebrainPersistentUnit")
	EntityManager em;
	
	public WorkspaceManagementService(){}
	
	@Override
	public void addWorkspace(Workspace workspace, User owner){
		workspace.initAccessList(owner);
		em.persist(workspace.getAccesslist());
		em.persist(workspace);
		em.flush();
	}

	@Override
	public void addMember(User user, int workspaceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Workspace> getWorkspacesByUserID(User user) {
		return (List<Workspace>)em.createNamedQuery("find workspaces by user").setParameter("owner", user).getResultList();
	}

	@Override
	public Workspace getById(int workspaceId) {
		return em.find(Workspace.class, workspaceId);
	}

	@Override
	public Workspace getByName(String name) {
		Query query = em.createQuery("SELECT w FROM Workspace w WHERE w.name = :name").setParameter("name", name);
		return (Workspace)query.getSingleResult();
	}

}
