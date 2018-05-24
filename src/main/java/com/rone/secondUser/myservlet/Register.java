package com.rone.secondUser.myservlet;

import com.rone.secondUser.myUtil.DBOperation;
import com.rone.secondUser.myUtil.MyStringUtil;
import com.rone.secondUser.mybean.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("serial")
public class Register extends HttpServlet{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//super.doPost(req, resp);
		req.setCharacterEncoding("gb2312");
		HttpSession session = req.getSession(true);
		session.setMaxInactiveInterval(-1);
		RequestDispatcher dispatcher = req.getRequestDispatcher("Register.jsp");
		
		User user = new User();
		String id = req.getParameter("id").trim();
		String pass = req.getParameter("pass").trim();
		String name = req.getParameter("name").trim();
		String tel = req.getParameter("tel").trim();
		
		try {
			pass = MyStringUtil.encryption(pass);
		} catch (NoSuchAlgorithmException e1) {
			log.error(e1);
		}
		
		DBOperation db = new DBOperation();
		String SQL = "select * from user where id ='"+id+"'";
		//Determine whether the user exists
		db.setSQL(SQL);
		boolean isExists = db.determineExists();
		if (isExists) {
			req.setAttribute("rigisterError", "该账号已存在");
			log.info("id:" + id + "注册失败, 原因: 该账号已存在");
			req.setAttribute("id", id);
			dispatcher.forward(req, resp);
			return;
		} else {
			SQL = "insert into user values ('"+id+"','"+pass+"','"+name+"','"+tel+"')";
			db.setSQL(SQL);
			db.executeOthersSQL();
			user.setId(id);
			user.setPass(pass);
			user.setName(name);
			user.setTel(tel);
			
			session.setAttribute("user", user);
			resp.sendRedirect("index.jsp");
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//super.doGet(req, resp);
		this.doPost(req, resp);
	}

}
