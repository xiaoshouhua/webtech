package com.my.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="CodeMarkerServlet",urlPatterns="/codeMarkerServlet")
public class CodeMarkerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2896679102543873417L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//首先设置页面不缓存
	    response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        
        //定义图片宽度,高度
        int width=120,height=30;
        
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);//不透明图片
        
        /**
         *得到画板,得到笔,
         *设置背景颜色,字体,字体颜色(最好是随机生成),以及干扰线,生成文字的位置<建议位置随机生成,增加破解的难度>
         */
        Graphics g = image.createGraphics();//得到图形的绘制环境
        g.setColor(getRandomColor(180,250));//设置背景颜色
        g.fillRect(0, 0, width, height);//绘制矩形区域
        
        //生成文字
        g.setFont(new Font("宋体",Font.ITALIC,30));//字体,字体风格,字体大小
        
        //随机字符串
        StringBuffer sb=new StringBuffer();
        
        Random r=new Random();//获得随机数对象
        
      //生成四位数字
        for(int i=0;i<4;i++){
        	//产生随机数
        	String randString = String.valueOf(r.nextInt(10));
        	sb.append(randString);
        	
        	int min=20+r.nextInt(80);
        	int max=100+r.nextInt(100);
        	//设置文字颜色
        	g.setColor(getRandomColor(min,max));
        	
        	//将文字写入图片,设置动态位置
        	//此处根据页面要求调整大小
        	g.drawString(randString, (17+r.nextInt(3))*i+8, 30);
        }
        
        //干扰线
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
    	g.setColor(getRandomColor(160, 200));
    	for (int i = 0; i < 155; i++) {
    		int x = r.nextInt(width);
    		int y = r.nextInt(height);
    		int xl = r.nextInt(12);
    		int yl = r.nextInt(12);
    		g.drawLine(x, y, x + xl, y + yl);
    	}
    	/**
    	 * 加一些特效
    	 * Graphics2D g2d=(Graphics2D)g;
    	 *  g2d.rotate(-3*Math.PI/180,0,20);
    	 */
    	
        g.dispose();//图片生成
        
        request.getSession().setAttribute("verfityCode",sb.toString());
        
        ImageIO.write(image, "jpeg", response.getOutputStream());
	}
	
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	//生成一个随机的颜色
	private  Color getRandomColor(int minColor,int maxColor){
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
		
		/**
		 * 0-255是颜色数字编码区间
		 * 0-7-blue 
		 * 8-15-green
		 * 16-23 red
		 * 等等
		 */
		
		return new Color(red,green,blue);
	}
	
	
}
