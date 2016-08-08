package com.onebrain.content;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ContentManagementService implements ContentManagerInterface{
	
	@PersistenceContext(unitName="MySQLOnebrainPersistentUnit")
	EntityManager em;
	
	public ContentManagementService(){}

	@Override
	public Idea addIdea(Idea idea) {
		//idea.setAuthor(author);
		//idea.setWorkspace(workspace);
		em.persist(idea);
		return idea;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Idea> getIdeasByWorkspace(String wName) {
		Query query = em.createQuery("SELECT i FROM Idea i WHERE i.workspace.name = :wName", Idea.class).setParameter("wName",wName);
		return (List<Idea>)query.getResultList();
	}

}
