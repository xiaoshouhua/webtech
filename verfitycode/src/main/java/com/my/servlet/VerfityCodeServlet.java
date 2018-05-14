package com.my.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="VerfityCodeServlet",urlPatterns="/verfity")
public class VerfityCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*建议不要把0,o同时放入,建议二其一*/
	private static String codeChars="%#0123456789abczefghijklmnopqrstuvwxyzABCZEFGHIJKLMNOPQRSTUVWXYZ";
	
	//返回一个随机颜色
	private static Color getRandomColor(int minColor,int maxColor){
		Random random=new Random();
		
		if(minColor>255){
			minColor=255;
		}
		if(maxColor>255){
			maxColor=255;
		}
		
		//颜色的三原色:红,绿,蓝<R,G,B>
		//获得红色的随机颜色值
		int red = minColor + random.nextInt(maxColor - minColor);
		//获得绿色的随机颜色值
		int green = minColor + random.nextInt(maxColor - minColor);
		//获得蓝色的随机颜色值
		int blue = minColor + random.nextInt(maxColor - minColor);
		
		return new Color(red,green,blue);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得验证码长度的集合
		int length = codeChars.length();
		//关闭客户端浏览器的缓冲区
		//这三条语句都可以关闭浏览器的缓冲区,但是由于浏览器的版本不同,对着三条语句的支持也不同
		//所以为了保险起见,建议同时使用这3条语句关闭浏览器的缓冲区
		response.setHeader("Pragma", "no-cache");//TODO ragma
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		//设置验证码的长和宽
		int width  = 90, height = 20;
		BufferedImage image=new  BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//获得用于输出文字的Graphics对象
		Graphics g=image.getGraphics();
		Random random=new Random();
		g.setColor(getRandomColor(180,250));//随机设置要填充的颜色
		g.fillRect(0, 0, width, height);//填充图形背景
		//设置初始字体
		g.setFont(new Font("Times New Roman",Font.ITALIC,height));
		g.setColor(getRandomColor(120,180));//随机设置字体颜色
		//用户保存最后随机生成的验证码
		StringBuilder sb=new StringBuilder();
		//验证码的随机字体
		String[] fontNames={"Times New Roman","Book antiqua","Arial"};
		
		//随机生成3到5位验证码
		for(int i=0;i<3 + random.nextInt(3);i++){
			//随机设置当前验证码的字符的字体
			g.setFont(new Font(fontNames[random.nextInt(3)],Font.ITALIC,height));
			//随机获得当前验证码的字符
			char codeChar=codeChars.charAt(random.nextInt(length));
			sb.append(codeChar);
			//随机设置当前验证码的颜色
			g.setColor(getRandomColor(10,100));
			//在图形上输出验证码字符,x和y都是随机生成的
			g.drawString(String.valueOf(codeChar), 16*i+random.nextInt(7),height - random.nextInt(6));
		}
		
		//获得HttpSession对象
		HttpSession session=request.getSession();
		//设置session失效时间
		session.setMaxInactiveInterval(300);
		//将验证码保存到session中
		session.setAttribute("verfity_code",sb.toString());
		g.dispose();//关闭Graphics对象
		OutputStream os=response.getOutputStream();
		//以JPEG格式向客户端发送图形验证码
		ImageIO.write(image,"JPEG",os);
	}
	
	

}
