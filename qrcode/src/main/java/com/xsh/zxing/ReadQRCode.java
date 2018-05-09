package com.xsh.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class ReadQRCode {

	public static void main(String[] args) {

		MultiFormatReader formatReader = new MultiFormatReader();
		
		File file = new File("");
		File imgFile = new File(file.getAbsolutePath()+"/img.jpg");
		System.out.println(imgFile.getAbsolutePath());
		try {
		
			BufferedImage bufferedImg = ImageIO.read(imgFile);
		
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImg)));
			
			HashMap hints = new HashMap();
			hints.put(DecodeHintType.CHARACTER_SET,"utf-8");
			//优化精度
			hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
			//复杂模式，开启PURE_BARCODE模式
			hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
			Result result = formatReader.decode(bitmap, hints);
//			
			System.out.println(result);
			
			System.out.println("二维码解析结果:"+result.toString());
			System.out.println("二维码文本:"+result.getText());
			System.out.println("二维码格式:"+result.getBarcodeFormat());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
