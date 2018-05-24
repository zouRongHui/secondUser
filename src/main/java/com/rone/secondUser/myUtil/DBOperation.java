package com.rone.secondUser.myUtil;

import com.sun.rowset.CachedRowSetImpl;
import org.apache.log4j.Logger;

import java.sql.*;

public class DBOperation {
	private String SQL = "";
	private Logger log = Logger.getLogger(this.getClass());
	
	public void setSQL(String sQL) {
		SQL = sQL.trim();
	}
	
	public DBOperation() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			log.error("数据库连接建立失败 " + e);
		}
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/second_user", "root", "root");
		} catch (SQLException e) {
			log.error("数据库连接失败 " + e);
		}
		return con;
	}
	
	public boolean determineExists() {
		Connection con;
		PreparedStatement sql;
		ResultSet rs;
		
		try {
			con = this.getConnection();
			sql = con.prepareStatement(SQL);
			log.info(SQL);
			rs = sql.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			} else {
				con.close();
				return false;
			}
		} catch (SQLException e) {
			log.error("数据库操作失败" + e);
			return false;
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean executeOthersSQL() {
		Connection con;
		PreparedStatement sql;
		
		try {
			con = this.getConnection();
			sql = con.prepareStatement(SQL);
			log.info(SQL);
			sql.execute();
			con.close();
			return true;
		} catch (SQLException e) {
			log.error("数据库操作失败 " + e);
			return false;
		}
		
	}
	
	public CachedRowSetImpl executeQuerySQL() {
		Connection con;
		PreparedStatement sql;
		ResultSet rs;
		CachedRowSetImpl rowset = null;
		
		try {
			con = this.getConnection();
			sql = con.prepareStatement(SQL);
			log.info(SQL);
			rs = sql.executeQuery();
			rowset = new CachedRowSetImpl();
			rowset.populate(rs);
			con.close();
			return rowset;
		} catch (SQLException e) {
			log.error("数据库操作失败 " + e);
			return rowset;
		}
		
	}
	
	
}
