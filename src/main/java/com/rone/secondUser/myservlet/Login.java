package com.rone.secondUser.myservlet;

import com.rone.secondUser.myUtil.DBOperation;
import com.sun.rowset.CachedRowSetImpl;
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
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Login extends HttpServlet{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.println(this.getClass().getName() + " init");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession(true);
		session.setMaxInactiveInterval(-1);
		RequestDispatcher dispatcher = req.getRequestDispatcher("Welcome.jsp");
		
		User user = new User();
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		
//		try {
//			pass = MyStringUtil.encryption(pass);
//		} catch (NoSuchAlgorithmException e1) {
//			log.error(e1);
//		}
		
		user.setId(id);
		user.setPass(pass);
		
		DBOperation db = new DBOperation();
		String SQL = "select * from user where id = '"+id+"' and pass = '"+pass+"'";
		db.setSQL(SQL);
		CachedRowSetImpl rowset = db.executeQuerySQL();
		try {
			if (rowset.next()) {
				user.setName(rowset.getString("name"));
				user.setTel(rowset.getString("tel"));
				session.setAttribute("user", user);
				resp.sendRedirect("index.jsp");
			} else {
				req.setAttribute("loginError", "账号或密码存在错误");
				log.info("id:" + id + " pass:"+ pass + "登录失败, 原因: 账号或密码存在错误");
				dispatcher.forward(req, resp);
			}
		} catch (SQLException e) {
			log.error(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
		this.doPost(req, resp);
	}

}
