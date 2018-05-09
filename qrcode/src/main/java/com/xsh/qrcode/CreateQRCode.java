package com.xsh.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.swetake.util.Qrcode;

public class CreateQRCode {

	public static void createQRCode(String content, String imgPath) throws IOException {
		int width = 140;
		int height = 140;
		int x = 0;
		int y = 0;
		Qrcode qr = new Qrcode();
		//设置排错率(L-7%)、(M-15%)、(Q-25%)、(H-30%)
		qr.setQrcodeErrorCorrect('M');
		qr.setQrcodeEncodeMode('B');
		//设置二维码的尺寸 取值范围[1-40]
		qr.setQrcodeVersion(7);
		//设置图片的尺寸
		BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		//绘制二维码图片
		Graphics2D gs = bufImage.createGraphics();
		//设置二维码矩形区域的背景颜色,设置为白色
		gs.setBackground(Color.white);
		//创建一个图片的矩形局域
		gs.clearRect(x, y, width, height);
		//设置二维码的颜色
		gs.setColor(Color.black);
		//设置编码
		byte[] contentBytes = content.getBytes("gb2312");

		// 设置偏移量 不设置可能导致解析出错               
		int pixoff = 2;
		// 输出内容 > 二维码  
		if (contentBytes.length > 0 && contentBytes.length < 120) {
			boolean[][] codeOut = qr.calQrcode(contentBytes);
			for (int i = 0; i < codeOut.length; i++) {
				for (int j = 0; j < codeOut.length; j++) {
					if (codeOut[j][i]) {
						gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
					}
				}
			}
		} else {
			System.err.println("QRCode content bytes length=" + contentBytes.length + " not in [ 0,120 ].");
		}
		gs.dispose();
		bufImage.flush();
		//生成二维码
		File imgfile = new File(imgPath);
		ImageIO.write(bufImage, "png", imgfile);
		System.out.println("生成二维码成功,文件所在目录为:"+imgfile.getAbsolutePath());
	}

	public static void main(String[] args) {
		String content = "我是你大爷";
		
		File file = new File("");
		String imgpath = file.getAbsolutePath()+"/java.png";
		try {
			createQRCode(content, imgpath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
