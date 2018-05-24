package com.rone.secondUser.myservlet;

import com.rone.secondUser.myUtil.DBOperation;
import com.sun.rowset.CachedRowSetImpl;
import com.rone.secondUser.mybean.ShowAllGoods;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
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
public class SearchGoods extends HttpServlet{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession(true);
		session.setMaxInactiveInterval(-1);
		
		String name = req.getParameter("name")==null ? "" : req.getParameter("name");
		String category = req.getParameter("category")==null ? "" : req.getParameter("category");
		
		String SQL = "select * from goods where amount > 0";
		if (!name.equals("")) {
			SQL += " and name like '%"+name+"%'";
		}
		if (!category.equals("")) {
			SQL += " and category = '"+category+"'";
		}
		SQL += " order by price";
		
		DBOperation db = new DBOperation();	
		db.setSQL(SQL);
		CachedRowSetImpl rowset = db.executeQuerySQL();
		ShowAllGoods allGoods = new ShowAllGoods();
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
		
		allGoods.setAllPage(allPage);
		allGoods.setNowPage(1);
		allGoods.setLists(lists);
		allGoods.setHasLists(true);
		
		session.setAttribute("allGoods", allGoods);
		resp.sendRedirect("index.jsp");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
		this.doPost(req, resp);
	}

}
