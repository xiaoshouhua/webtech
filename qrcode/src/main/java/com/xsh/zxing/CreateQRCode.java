package com.xsh.zxing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateQRCode {

	public static void main(String[] args) {

		int width = 300;
		int height = 300;
		
		String format = "jpg";
		String content = "i love you";
		
		HashMap hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.MARGIN, 2);
		
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width, height, hints);
			
			File file = new File("");
			File imgFile = new File(file.getAbsolutePath()+"/img.jpg");
			
			MatrixToImageWriter.writeToFile(bitMatrix, format,imgFile);

			System.out.println("利用zxing生成二维码成功!二维码地址为:"+imgFile.getAbsolutePath());
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
	}

}
