package com.xsh.encrypt.md;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 消息摘要算法之MD
 * @author shouhua.xiao
 *
 */
public class MDTest {
	
	private static final String text = "i love you,qcj";

	public static void main(String[] args) {
		
		jdkMD2();
		jdkMD5();
		
		bouncyCastleMD2();
		bouncyCastleMD5();
		bouncyCastleMD4();
		bouncyCastleMD4_2();
		
		commonsCodecMd2();
		commonsCodecMd5();
	}
	
	
	private static void jdkMD2() {
		try {
			MessageDigest md2 = MessageDigest.getInstance("MD2");
			byte[] md2Bytes = md2.digest(text.getBytes());
			System.out.println("JDK MD2 ::: "+Hex.encodeHexString(md2Bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private static void jdkMD5() {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md5.digest(text.getBytes());
			System.out.println("JDK MD5 ::: "+Hex.encodeHexString(md5Bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private static void bouncyCastleMD2(){
			
		Digest md2 = new MD2Digest(); 
		
		md2.update(text.getBytes(), 0, text.getBytes().length);
		
		byte[] md2Bytes = new byte[md2.getDigestSize()];
		md2.doFinal(md2Bytes, 0);
		
		System.out.println("Bouncy Castle MD2 ::: "+org.bouncycastle.util.encoders.Hex.toHexString(md2Bytes));
	}
	
	private static void bouncyCastleMD4(){
		
		Digest md4 = new MD4Digest(); 
		
		md4.update(text.getBytes(), 0, text.getBytes().length);
		
		byte[] md4Bytes = new byte[md4.getDigestSize()];
		md4.doFinal(md4Bytes, 0);
		
		System.out.println("Bouncy Castle MD4 ::: "+org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
	}
	
	private static void bouncyCastleMD4_2(){
		
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest md4 = MessageDigest.getInstance("MD4");
			byte[] md4Bytes = md4.digest(text.getBytes());
			System.out.println("Bouncy Castle MD4 ::: "+org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}

	private static void bouncyCastleMD5(){
		
		Digest md5 = new MD5Digest(); 
		
		md5.update(text.getBytes(), 0, text.getBytes().length);
		
		byte[] md5Bytes = new byte[md5.getDigestSize()];
		md5.doFinal(md5Bytes, 0);
		
		System.out.println("Bouncy Castle MD5 ::: "+org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
	}
	
	private static void commonsCodecMd2() {
		System.out.println("Commons Codec MD2:"+DigestUtils.md2Hex(text));
	}
	
	private static void commonsCodecMd5() {
		System.out.println("Commons Codec MD5:"+DigestUtils.md5Hex(text));
	}
}

