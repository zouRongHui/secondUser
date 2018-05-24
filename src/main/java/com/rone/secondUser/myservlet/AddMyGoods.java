package com.rone.secondUser.myservlet;

import com.rone.secondUser.myUtil.DBOperation;
import com.rone.secondUser.mybean.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
public class AddMyGoods extends HttpServlet{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
		req.setCharacterEncoding("utf-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher("AddMyGoods.jsp");
		HttpSession session = req.getSession(true);
		session.setMaxInactiveInterval(-1);
		
		User user = (User) session.getAttribute("user");
		String owner = user.getId();

		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String category = req.getParameter("category");
		String amount = req.getParameter("amount");
		String picture = "picture";
		
		DBOperation db = new DBOperation();
		String SQL = "select * from goods where name ='"+name+"' and price='"+price+"'";
		db.setSQL(SQL);
		if (db.determineExists()) {
			req.setAttribute("addGoodsError", "该商品以上架");
			log.info(name + "商品上架失败, 原因: 该商品以上架");
			dispatcher.forward(req, resp);
		} else {
			SQL = "insert into goods(name,category,amount,price,owner,picture) "
					+ "values('"+name+"','"+category+"','"+amount+"','"+price+"','"+owner+"','"+picture+"')";
			db.setSQL(SQL);
			db.executeOthersSQL();
			
			resp.sendRedirect("SearchMyGoods");
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
		this.doPost(req, resp);
	}

}
