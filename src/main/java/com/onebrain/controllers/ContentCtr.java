package com.onebrain.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.onebrain.content.ContentManagerInterface;
import com.onebrain.content.Idea;
import com.onebrain.user.User;
import com.onebrain.user.UserManagerInterface;
import com.onebrain.workspace.Workspace;
import com.onebrain.workspace.WorkspaceManagerInterface;

/**
 * Servlet implementation class ContentCtr
 */
@WebServlet(urlPatterns= {"/ContentCtr", "workspaces/*", "workspaces/*/evaluation"})
public class ContentCtr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ContentManagerInterface contentMS;
	
	@EJB
	private UserManagerInterface userMS;
	
	@EJB
	private WorkspaceManagerInterface workspaceMS;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentCtr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("SERVLET PATH"+request.getServletPath());
		//System.out.println("PATH INFO"+request.getPathInfo());
		if(request.getParameter("action") != null){
			switch (request.getParameter("action")) {

			case "none":
				//System.out.println("PRUEBA NONE");
				//System.out.println(request.getAttribute("workspace"));
				request.getSession().setAttribute("workspaceLocation", (String)request.getParameter("workspace"));
				response.getWriter().print(request.getParameter("workspace"));
				break;
			case "getSolutions":
				//System.out.println("GET CONTNTCTR");
				List<Idea> iList = contentMS.getIdeasByWorkspace((String)request.getSession().getAttribute("workspaceLocation"));
				//System.out.println("getideasbyworkspace: "+iList);
				String jsonString = new Gson().toJson(iList);
				////System.out.print("GSON FROM CONTENTCTR: "+jsonString);
				response.getWriter().print(jsonString);
				break;
			}			
		}
		else {
			if(request.getPathInfo().contains("evaluation")){
				//System.out.println("ENTRO");
				request.getServletContext().getRequestDispatcher("/WEB-INF/views/evaluations.html").forward(request, response);
			}
			else{
				request.getServletContext().getRequestDispatcher("/WEB-INF/views/solutions.html").forward(request, response);
			}
			
		}
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getParameter("action")) {
		case "createIdea":
			Idea idea = newIdeaAction(request);
			response.getWriter().print("IDEA ADDED");
			break;

		default:
			break;
		}
		
		/**switch (request.getParameter("action")) {
		case "create":
			Workspace ws = createWorkspace(request);
			//System.out.println((Integer.parseInt(request.getSession().getAttribute("userId").toString())));
			User owner = createUser((int)request.getSession().getAttribute("userId"));
			System.out.println("FRONT WCTR LINE 60: USER FROM METHOS CREATEUSER:"+owner.toString());
			System.out.println("FRONT WCTR LINE 61, ID MANUALLY GENERATOR:"+owner.getId());
			workspaceMS.addWorkspace(ws, owner);
			response.getWriter().print("EXITO");
			break;
		case "getWorkspaces":
			List<Workspace> wsList = getWorkspaces(Integer.parseInt(((String)request.getSession().getAttribute("userId"))));
			String jsonString = new Gson().toJson(wsList);
			System.out.print(jsonString);
			response.getWriter().print(jsonString);
			break;
		}*/
		
		doGet(request, response);
	}
	
	public Idea newIdeaAction(HttpServletRequest request){
		return contentMS.addIdea(createIdea(request));
	}
	
	public Idea createIdea(HttpServletRequest request){
		return new Idea((String)request.getParameter("iTitle"), (String)request.getParameter("iDescription"), userMS.getById((int)request.getSession().getAttribute("userId")), workspaceMS.getByName((String)request.getSession().getAttribute("workspaceLocation")));
	}

}
