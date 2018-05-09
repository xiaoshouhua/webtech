package com.xsh.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;
import jp.sourceforge.qrcode.QRCodeDecoder;
public class ReadQRCode {

	public static void main(String[] args) {

		QRCodeDecoder decoder = new QRCodeDecoder();
		
		File file = new File("");
		File imgFile = new File(file.getAbsolutePath()+"/java.png");
		
		BufferedImage bufferedImg = null;
		try {
			bufferedImg = ImageIO.read(imgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = decoder.decode(new MYQRCodeImage(bufferedImg));
		try {
			System.out.println(new String(bytes,"gb2312"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

}
