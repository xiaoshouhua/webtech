package com.xsh.encrypt.des;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class DESTest {
	
	private static final String text = "i love you,qcj";

	public static void jdkDES() {
		try {
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			//设置密钥长度
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			
			//key转换
			DESKeySpec desKeySpec = new DESKeySpec(byteKey);
			SecretKeyFactory  factory = SecretKeyFactory.getInstance("DES");
			Key convertKey = factory.generateSecret(desKeySpec);
			
			//加密算法/工作方式/填充方式
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertKey);
			byte[] result = cipher.doFinal(text.getBytes());
			System.out.println("jdk des sign:::"+Hex.toHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertKey);
			byte[] data = cipher.doFinal(result);
			System.out.println("jdk des decypt:::"+new String(data));
			
		} catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		jdkDES();
		
		bcDES();
	}

	private static void bcDES() {
		try {
			
			Security.addProvider(new BouncyCastleProvider());
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
			//keyGenerator.getProvider();
			//设置密钥长度
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			
			//key转换
			DESKeySpec desKeySpec = new DESKeySpec(byteKey);
			SecretKeyFactory  factory = SecretKeyFactory.getInstance("DES");
			Key convertKey = factory.generateSecret(desKeySpec);
			
			//加密算法/工作方式/填充方式
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertKey);
			byte[] result = cipher.doFinal(text.getBytes());
			System.out.println("BouncyCastle DES sign:::"+Hex.toHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertKey);
			byte[] data = cipher.doFinal(result);
			System.out.println("BouncyCastle DES decypt:::"+new String(data));
			
		} catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
			e.printStackTrace();
		}
	}
}
