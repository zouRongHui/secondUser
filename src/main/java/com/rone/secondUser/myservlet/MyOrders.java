package com.rone.secondUser.myservlet;

import com.rone.secondUser.myUtil.DBOperation;
import com.sun.rowset.CachedRowSetImpl;
import com.rone.secondUser.mybean.ShowMyOrders;
import com.rone.secondUser.mybean.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class MyOrders extends HttpServlet{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.service(req, resp);
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession(true);
		session.setMaxInactiveInterval(-1);
		
		User user = (User) session.getAttribute("user");
		String owner = user.getId();
		
		DBOperation db = new DBOperation();
		String SQL = "select * from myorder where purchaser='"+owner+"' or seller='"+owner+"'";
		db.setSQL(SQL);
		CachedRowSetImpl rowset = db.executeQuerySQL();
		
		ShowMyOrders myOrders = new ShowMyOrders();
		ArrayList<HashMap<String, String>> lists = new ArrayList<>();
		int allPage = 0;
		
		try {
			int allRecord = 0;
			ResultSetMetaData rsmd = rowset.getMetaData();
			int n = rsmd.getColumnCount();
			while(rowset.next()) {
				HashMap<String, String> hm = new HashMap<>();
				for(int i = 1; i <= n; i++) {
					String str = rowset.getString(i)==null ? "" : rowset.getString(i).trim();
					hm.put(rsmd.getColumnName(i), str);
				}
				lists.add(hm);
				allRecord++;
			}
			allPage = allRecord % 8 + 1;
		} catch (SQLException e) {
			log.error(e);
		}
		
		myOrders.setAllPage(allPage);
		myOrders.setNowPage(1);
		myOrders.setLists(lists);
		
		session.setAttribute("myOrders", myOrders);
		resp.sendRedirect("MyOrders.jsp");
	}
}
