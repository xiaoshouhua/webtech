package com.xsh.encrypt.rsa;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Hex;

/**
 * 数字签名算法之RSA
 * @author shouhua.xiao
 *
 */
public class RSATest {
	
	private static final String text = "i love you,qcj";

	public static void main(String[] args) {
		try {
			
			//1.初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey= (RSAPublicKey) keyPair.getPublic();//获得公钥
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();//获得私钥
			
			//2.生成签名
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("MD5withRSA");
			signature.initSign(privateKey);
			signature.update(text.getBytes());
			byte[] result = signature.sign();
			System.out.println("jdk rsa sign ::: "+Hex.encodeHexString(result));
			
			//3.验证签名
			X509EncodedKeySpec X509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(X509EncodedKeySpec);
			signature = Signature.getInstance("MD5withRSA");
			signature.initVerify(publicKey);
			signature.update(text.getBytes());
			boolean flag = signature.verify(result);
			System.out.println("jdk rsa verify ::: "+flag);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
		
		
	}

}
