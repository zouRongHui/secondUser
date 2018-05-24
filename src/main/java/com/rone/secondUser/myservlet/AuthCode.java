package com.rone.secondUser.myservlet;

import com.rone.secondUser.myUtil.MyStringUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@SuppressWarnings("serial")
public class AuthCode extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
		resp.setContentType("image/jpeg");
		
		//获取前台传过来的验证码
		String authCode = req.getParameter("authCode").trim();
		int[] ranNum = MyStringUtil.stringToInts(authCode);
//		System.out.println("authCode" + authCode);
//		for (int i =0; i < ranNum.length; i++) {
//			System.out.print(ranNum[i] + "..");
//		}
//		System.out.println();
		
		//创建图像
		//设置图片大小
		int num = ranNum.length;
		int width = 20 * (num +3);
		int height = 10 * num;
		//设置干扰元素数量
		int lines = ranNum.length;
		//绘制背景
		//创建BufferedImage对象
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//创建图像上下文对象
		Graphics2D g = (Graphics2D) img.getGraphics();
		//绘制边框
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		//绘制验证码
		//设置字体
		g.setFont(new Font("宋体", Font.BOLD, 20));
		//创建Random对象
		Random ran = new Random();
		//生成随机验证码并添加进图像中
		int r = 0;
		int x = 0;
		int y = 30;
		AffineTransform Tx = new AffineTransform();
		Color c;
		for (int i = 0; i < num; i++){
			x = 20 + i * 25;
			//设置旋转
			r = 300 + ran.nextInt(120);
			Tx.rotate(r * Math.PI / 180, x + 10, y - 10);
			g.setTransform(Tx);
			//添加验证码
			c = new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255));
			g.setColor(c);
			g.drawString(""+ranNum[i], x, y);
			//还原旋转
			Tx.rotate((720 - r) * Math.PI / 180, x + 10, y - 10);
			g.setTransform(Tx);
		}
		//绘制干扰元素
		for (int i = 0; i < lines; i++) {
			c = new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255));
			g.setColor(c);
			//干扰元素为划线
			g.drawLine(20, ran.nextInt(height), width - 20, ran.nextInt(height));
		}
		//释放图像上下文，生成图像
		g.dispose();
		//将图像输出到页面
		ImageIO.write(img, "JPG", resp.getOutputStream());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
		this.doPost(req, resp);
	}

}
