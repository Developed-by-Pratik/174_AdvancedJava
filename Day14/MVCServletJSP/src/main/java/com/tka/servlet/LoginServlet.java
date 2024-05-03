package com.tka.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.tka.dao.StudentDAO;
import com.tka.entity.Student;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		System.out.println("U r in do post Method...");
		
		String username= request.getParameter("username");
		String password=request.getParameter("password");
		
		StudentDAO dao=new StudentDAO();
		Student stud= dao.checkUser(username, password);
		
		PrintWriter pw= response.getWriter();
		
		HttpSession session= request.getSession();
		
		
		
		if(stud!=null) {
			//pw.write("Valid User...");
			System.out.println("Valid User");
			session.setAttribute("username", stud.getUsername());
//			RequestDispatcher rd= request.getRequestDispatcher("DashboardServlet");			
//			rd.forward(request, response);
			
			response.sendRedirect("DashboardServlet");
			
		}else {
			RequestDispatcher rd= request.getRequestDispatcher("login.jsp");
			response.setContentType("text/html");
			pw.write("<h2>Invalid User</h2>");
			
			rd.include(request, response);
			
			//pw.write("Invalid User....");
			
			
		}
		
		
		
		
		
		
		
		
		
		
	}

}
