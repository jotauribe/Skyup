package com.onebrain.user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


//@EJB(name="name", beanInterface=UserManagerInterface.class)
@Stateless
public class UserManagementService implements UserManagerInterface{

	@PersistenceContext(unitName="MySQLOnebrainPersistentUnit")
	private EntityManager em;
	
	
	public UserManagementService(){}
	
	public User createUser(User user) {
		em.persist(user);
		return user;
	}
	
	@Override
	//@Transactional
	public void insert(){
		//em = emf.createEntityManager();
		User user = new User("manolo", "email", "password");
		//em.createNativeQuery("INSERT INTO users(nickname, email, password) VALUES('nickname', 'email', 'password')");
		em.persist(user);
		User user2 = new User("name2", "email2", "password2");
		em.persist(user2);
		User u = em.find(User.class, 2);
		//em.flush();
		try{
			//System.out.println(i);
			System.out.println(u.getName());
			//System.out.println(users.get(0).getName());
		}catch(Exception e){
			System.out.println("ERROR: "+ e.toString());
		}
	}



	@Override
	public User getById(int id) {
		return em.find(User.class, id);
	}



	@Override
	public User validateUser(String name, String password) {
		javax.persistence.Query query =  em.createNamedQuery("userValidationQuery");
		query.setParameter("userName", name);
		System.out.print(name);
		query.setParameter("userPassword", password);
		System.out.print(password);
		User user = (User)query.getResultList().get(0);
		System.out.print(user.toString());
		return user;
		
	}
	
	
	
}
