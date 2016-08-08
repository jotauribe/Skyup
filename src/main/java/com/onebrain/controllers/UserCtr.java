package com.onebrain.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onebrain.user.UserManagementService;
import com.onebrain.user.UserManagerInterface;
import com.onebrain.user.User;


/**
 * Servlet implementation class UserCtr
 */

@WebServlet("/UserCtr")
public class UserCtr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserManagerInterface userMS;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCtr() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//userMS = new UserManagementService();
		//userMS.insert();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getParameter("action")) {
		case "signup":
			//System.out.println(request.getParameter("name"));
			//System.out.println(request.getParameter("password"));
			User user = createUser(request);
			//System.out.println(user.toString());
			signupAction(user, request.getSession());
			response.getWriter().print("EXITO");
			break;
		case "login":
			User logedUser = logInAction(request);
			HttpSession session = request.getSession();
			session.setAttribute("userId", logedUser.getId());
			session.setAttribute("user", logedUser.getName());
			session.setAttribute("password", logedUser.getPassword());
			break;

		default:
			break;
		}
		doGet(request, response);
	}
	
	public void signupAction(User user, HttpSession session){
		
		userMS.createUser(user);
		session.setAttribute("userId", user.getId());
		session.setAttribute("user", user.getName());
		session.setAttribute("password", user.getPassword());
		//System.out.println(session.getAttribute("userId"));
		//System.out.println(session.getAttribute("user"));
		
	}
	
	public User logInAction(HttpServletRequest request){
		return userMS.validateUser((String)request.getParameter("nickname"), (String)request.getParameter("password"));
	}
	
	private User createUser(HttpServletRequest request){
		return new User(request.getParameter("name"), "email", request.getParameter("password"));
	}
}
