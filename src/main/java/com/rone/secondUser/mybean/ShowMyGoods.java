package com.rone.secondUser.mybean;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowMyGoods {
	private int nowPage;
	private int allPage;
	private ArrayList<HashMap<String, String>> lists;
	
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getAllPage() {
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	public ArrayList<HashMap<String, String>> getLists() {
		return lists;
	}
	public void setLists(ArrayList<HashMap<String, String>> lists) {
		this.lists = lists;
	}
}
