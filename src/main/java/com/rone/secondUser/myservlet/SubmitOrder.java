package com.rone.secondUser.myservlet;

import com.rone.secondUser.myUtil.DBOperation;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class SubmitOrder extends HttpServlet{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
		req.setCharacterEncoding("utf-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher("index_main.jsp");
	
		String goodsID = req.getParameter("goodsID");
		String goodsName = req.getParameter("goodsName");
		String amount = req.getParameter("amount");
		String purchaser = req.getParameter("purchaser");
		String seller = req.getParameter("seller");
		
		DBOperation db = new DBOperation();
		String SQL = "select * from myorder where goodsID='"+goodsID+"' and purchaser='"+purchaser+"'";
		db.setSQL(SQL);
		if (db.determineExists()) {
			req.setAttribute("feedback", "请勿重复提交订单！");
			log.info("订单提交失败, 原因: 重复提交订单！");
			dispatcher.forward(req, resp);
		} else {
			SQL = "insert into myorder(goodsID,goodsName,amount,purchaser,seller) values('"+goodsID+"','"+goodsName+"','"+amount+"','"+purchaser+"','"+seller+"')";
			db.setSQL(SQL);
			db.executeOthersSQL();
			
			req.setAttribute("feedback", "订单提交成功，祝您购物愉快！");
			
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
		this.doPost(req, resp);
	}
	
}
