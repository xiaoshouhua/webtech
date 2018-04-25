package com.xsh.encrypt.pbe;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.bouncycastle.util.encoders.Hex;

public class PBETest {
	
	private static final String password = "iloveqcj";

	public static void main(String[] args) {
		jdkPBE();
	}

	public static void jdkPBE() {
		//初始化盐
		SecureRandom random = new SecureRandom();
		byte[] salt = random.generateSeed(8);
		
		try {
			PBEKeySpec desKeySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWithMD5andDES");
			Key key = factory.generateSecret(desKeySpec);
			
			//加密
			PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,100);
			Cipher cipher = Cipher.getInstance("PBEWithMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key,pbeParameterSpec);
			byte[] result = cipher.doFinal(password.getBytes());
			System.out.println("jdk pbe sign:::"+Hex.toHexString(result));
			
			cipher.init(Cipher.DECRYPT_MODE, key,pbeParameterSpec);
			byte[] data = cipher.doFinal(result);
			System.out.println("jdk pbe decrypt:::"+new String(data));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

}
