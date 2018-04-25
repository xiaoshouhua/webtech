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
import javax.crypto.spec.DESedeKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class T3DESTest {
	
	private static final String text = "i love you,qcj";

	public static void jdk3DES() {
		try {
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			//设置密钥长度
			keyGenerator.init(168);
			//上行代码或者用keyGenerator.init(new SecureRandom());
			
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			
			//key转换
			DESedeKeySpec desKeySpec = new DESedeKeySpec(byteKey);
			SecretKeyFactory  factory = SecretKeyFactory.getInstance("DESede");
			Key convertKey = factory.generateSecret(desKeySpec);
			
			//加密算法/工作方式/填充方式
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertKey);
			byte[] result = cipher.doFinal(text.getBytes());
			System.out.println("JDK DES SIGN:::"+Hex.toHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertKey);
			byte[] data = cipher.doFinal(result);
			System.out.println("JDK DES decypt:::"+new String(data));
			
		} catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
	}
	
	public static void BC3DES() {
		try {
			Security.addProvider(new BouncyCastleProvider());
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede","BC");
			//设置密钥长度
			keyGenerator.init(168);
			//上行代码或者用keyGenerator.init(new SecureRandom());
			
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			
			//key转换
			DESedeKeySpec desKeySpec = new DESedeKeySpec(byteKey);
			SecretKeyFactory  factory = SecretKeyFactory.getInstance("DESede");
			Key convertKey = factory.generateSecret(desKeySpec);
			
			//加密算法/工作方式/填充方式
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertKey);
			byte[] result = cipher.doFinal(text.getBytes());
			System.out.println("Bouncy Castle DES SIGN:::"+Hex.toHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertKey);
			byte[] data = cipher.doFinal(result);
			System.out.println("Bouncy Castle DES decypt:::"+new String(data));
			
		} catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		jdk3DES();
		
		BC3DES();
	}

}
