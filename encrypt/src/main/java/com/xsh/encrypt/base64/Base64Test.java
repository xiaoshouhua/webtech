package com.xsh.encrypt.base64;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author shouhua.xiao
 *
 */
public class Base64Test {
	
	private static final String text = "i love you,qcj";

	/**
	 * 使用jdk自带方式实现Base64加解密
	 * @param args
	 */
	public static void jdkBase64() {
		System.out.println("-----------------1.使用jdk自带方式实现Base64加解密--------------------------");
		
		BASE64Encoder encoder = new BASE64Encoder();
		String encodeTxt = encoder.encode(text.getBytes());
		System.out.println("encoder:::"+encodeTxt);
		
		BASE64Decoder decoder = new BASE64Decoder();
		String decodeTxt;
		try {
			decodeTxt = new String(decoder.decodeBuffer(encodeTxt));
			System.out.println("decoder:::"+decodeTxt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 2.使用commons codec实现Base64加密/解密
	 */
	public static void commonsCodesBase64() {
		
		System.out.println("---------------2.使用commons codec实现Base64加密/解密----------------------------");
		
		byte[] encodeBytes = Base64.encodeBase64(text.getBytes());
		System.out.println("encoder:::"+new String(encodeBytes));
		
		byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
		System.out.println("decoder:::"+new String(decodeBytes));
	}
	
	/**
	 * 使用Bouncy Castle实现Base64加密/解密
	 */
	public static void bouncyCastleBase64() {
		
		System.out.println("---------------3.使用Bouncy Castle实现Base64加密/解密----------------------------");
		
		byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(text.getBytes());
		System.out.println("encoder:::"+new String(encodeBytes));
		
		byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
		System.out.println("decoder:::"+new String(decodeBytes));
	}
	
	public static void main(String[] args) {
		
		jdkBase64();
		
		commonsCodesBase64();
		
		bouncyCastleBase64();
		
	}

}
