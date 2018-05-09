package com.xsh.qrcode;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;


public class QrcodeImg {

	/**
	 * 方法名:QrcodeImg<BR>
	 * 创建人:Timer<BR>
	 * 时间:2015-12-6下午5:29:23<BR><BR>
	 * @throws IOException 
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static void qrcodeImg(String content,String imgPath) throws IOException{
		
		int width = 140;
		int height = 140;
		
		Qrcode qrcode=new Qrcode();
		
		//设置排错率L(7%)、M(15%)、Q(25%)、H(30%)
		qrcode.setQrcodeErrorCorrect('M');
		qrcode.setQrcodeEncodeMode('B');
		
		//设置二维码的尺寸（1--40）
		qrcode.setQrcodeVersion(7);
		//设置图片尺寸
		BufferedImage bufImg=new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
		
		//绘制二维码图片
		Graphics2D gs = bufImg.createGraphics();  
        // 设置矩形区域的背景颜色  
        gs.setBackground(Color.WHITE);  
        //创建一个二维码的矩形区域
        gs.clearRect(0, 0, width,height);  
        // 设定二维码图像的颜色==BLACK  
        gs.setColor(Color.BLACK);  
        // 设置偏移量，不设置可能导致解析出错  
        int pixoff = 2;
        
        //获取内容的数组字节,设置编码集
        byte[] contentBytes=content.getBytes("utf-8");
        
        // 输出内容> 二维码  
        if (contentBytes.length > 0 && contentBytes.length < 120) {
        	
            boolean[][] codeOut = qrcode.calQrcode(contentBytes);  
            
            for (int i = 0; i < codeOut.length; i++) {  
                for (int j = 0; j < codeOut.length; j++) {  
                    if (codeOut[j][i]) {  
                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                    }  
                }  
            }  
        }else{
        	System.err.println("出错了,内容超出限制!");
        }
        gs.dispose();  
        bufImg.flush();  
        
        File imageFile=new File(imgPath); 
        ImageIO.write(bufImg,"png",imageFile);
        //生成图片
        
        System.out.println("生成二维码成功,文件所在目录为:"+imageFile.getAbsolutePath());
	}
	
	
	public static void main(String[] args) throws IOException {
		File file = new File("");
		
		qrcodeImg("你是我的牙膏",file.getAbsolutePath()+"/1.png");
	}
}
