package com.rone.secondUser.myservlet;

import com.rone.secondUser.mybean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
public class Logout extends HttpServlet{
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.service(arg0, arg1);
		HttpSession session = req.getSession(true);
		
		User user = new User();
		session.setAttribute("user", user);
		
		resp.sendRedirect("Welcome.jsp");
	}
}
