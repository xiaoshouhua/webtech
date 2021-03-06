package com.xsh.encrypt.aes;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class AESTest {
	
	private static final String text = "i love you,qcj";
	
	public static void jdkAES() {
		try {
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			//设置密钥长度
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			
			//key转换
			Key convertKey = new SecretKeySpec(byteKey,"AES");
			
			//加密算法/工作方式/填充方式
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertKey);
			byte[] result = cipher.doFinal(text.getBytes());
			System.out.println("jdk aes sign:::"+Hex.toHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertKey);
			byte[] data = cipher.doFinal(result);
			System.out.println("jdk aes decypt:::"+new String(data));
			
		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
	}
	
	public static void BCAES() {
		try {
			
			Security.addProvider(new BouncyCastleProvider());
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES","BC");
			keyGenerator.getProvider();
			//设置密钥长度
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			
			//key转换
			Key convertKey = new SecretKeySpec(byteKey,"AES");
			
			//加密算法/工作方式/填充方式
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertKey);
			byte[] result = cipher.doFinal(text.getBytes());
			System.out.println("jdk aes sign:::"+Hex.toHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertKey);
			byte[] data = cipher.doFinal(result);
			System.out.println("jdk aes decypt:::"+new String(data));
			
		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		jdkAES();
		
		BCAES();
	}

}
