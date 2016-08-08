package com.onebrain.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.onebrain.user.User;
import com.onebrain.user.UserManagerInterface;
import com.onebrain.workspace.Workspace;
import com.onebrain.workspace.WorkspaceManagerInterface;

/**
 * Servlet implementation class WorkspaceCtr
 */
@WebServlet(urlPatterns = {"/WorkspaceCtr"})
public class WorkspaceCtr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	WorkspaceManagerInterface workspaceMS;
	
	@EJB
	UserManagerInterface userMS;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkspaceCtr() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getParameter("action")) {

		case "getWorkspaces":
			List<Workspace> wsList = getWorkspaces(Integer.parseInt((request.getSession().getAttribute("userId").toString())));
			//System.out.print(wsList.get(0).getName());
			//System.out.print(wsList.get(0).getAccesslist().getOwner());
			String jsonString = new Gson().toJson(wsList);
			//System.out.print(jsonString);
			response.getWriter().print(jsonString);
			break;
		case "getWorkspace":
			//System.out.println((String)request.getSession().getAttribute("workspaceLocation"));
			Workspace w = workspaceMS.getByName((String)request.getSession().getAttribute("workspaceLocation"));
			//String jsonObjectString = new Gson().toJson(w);
			System.out.print("OBJECT"+w.toString());
			//System.out.print("JSONOBJECT"+jsonObjectString);
			//NEW CODE
			javax.json.JsonObject jo = Json.createObjectBuilder().add("name", w.getName()).add("description", w.getDescription()).build();
			//NEW CODE END
			response.getWriter().print(jo);
			break;
		case "none":
			//System.out.println("PRUEBA NONE");
			//System.out.println(request.getAttribute("workspace"));
			request.getSession().setAttribute("workspaceLocation", (String)request.getParameter("workspace"));
			response.getWriter().print(request.getParameter("workspace"));
			break;
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getParameter("action")) {
		case "create":
			Workspace ws = createWorkspace(request);
			//System.out.println((Integer.parseInt(request.getSession().getAttribute("userId").toString())));
			User owner = createUser((int)request.getSession().getAttribute("userId"));
			//System.out.println("FRONT WCTR LINE 60: USER FROM METHOS CREATEUSER:"+owner.toString());
			//System.out.println("FRONT WCTR LINE 61, ID MANUALLY GENERATOR:"+owner.getId());
			workspaceMS.addWorkspace(ws, owner);
			response.getWriter().print("EXITO");
			break;
		case "getWorkspaces":
			List<Workspace> wsList = getWorkspaces(Integer.parseInt(((String)request.getSession().getAttribute("userId"))));
			String jsonString = new Gson().toJson(wsList);
			//System.out.print("getWorkspaces"+jsonString);
			response.getWriter().print(jsonString);
			break;
		}
		
		doGet(request, response);

	}
	
	public Workspace createWorkspace(HttpServletRequest request){
		
		return new Workspace(request.getParameter("wName"), request.getParameter("wDescription"));
	}
	
	public List<Workspace> getWorkspaces(int userId){
		return workspaceMS.getWorkspacesByUserID(userMS.getById(userId));
	}

	private User createUser(int userId){
		return userMS.getById(userId);
	}
}
