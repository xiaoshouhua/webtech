package com.xsh.encrypt.dsa;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 数字签名算法之DSA
 * @author shouhua.xiao
 *
 */
import org.apache.commons.codec.binary.Hex;

public class DSATest {
	
	private static final String text = "i love you,qcj";
	static DSAPublicKey dsaPublicKey = null;
	static DSAPrivateKey dsaPrivateKey = null;
	static {
		//1.初始化密钥
		KeyPairGenerator keyPairGenerator;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("DSA");
		
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			dsaPublicKey= (DSAPublicKey) keyPair.getPublic();//获得公钥
			dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();//获得私钥
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) {
		
		byte[] result = generate();
		
		verify(result);
	}

	public static byte[] generate() {
		try {
			//2.生成签名
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("DSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("SHA1withDSA");
			signature.initSign(privateKey);
			signature.update(text.getBytes());
			byte[] result = signature.sign();
			System.out.println("jdk dsa sign ::: "+Hex.encodeHexString(result));
			return result;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void verify(byte[] result) {
		try {
			//验证签名
			X509EncodedKeySpec X509EncodedKeySpec = new X509EncodedKeySpec(dsaPublicKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("DSA");
			PublicKey publicKey = keyFactory.generatePublic(X509EncodedKeySpec);
			Signature signature = Signature.getInstance("SHA1withDSA");
			signature.initVerify(publicKey);
			signature.update(text.getBytes());
			boolean flag = signature.verify(result);
			System.out.println("jdk dsa verify ::: "+flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
