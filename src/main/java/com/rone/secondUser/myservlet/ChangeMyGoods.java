package com.rone.secondUser.myservlet;

import com.rone.secondUser.myUtil.DBOperation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class ChangeMyGoods extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
		req.setCharacterEncoding("utf-8");
		
		String number = req.getParameter("number");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String category = req.getParameter("category");
		String amount = req.getParameter("amount");
		
		DBOperation db = new DBOperation();
		String SQL = "update goods set name='"+name+"',price='"+price+"',category='"+category+"',amount='"+amount+"' where number='"+number+"'";
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
