package com.rone.secondUser.myservlet;

import com.rone.secondUser.myUtil.DBOperation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class DeleteMyGoods extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
		req.setCharacterEncoding("utf-8");
		
		String number = req.getParameter("number");
		
		DBOperation db = new DBOperation();
		String SQL = "delete from goods where number='"+number+"'";
		db.setSQL(SQL);
		db.executeOthersSQL();
		
		resp.sendRedirect("SearchMyGoods");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
		this.doPost(req, resp);
	}

}
