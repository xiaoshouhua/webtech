package com.xsh.encrypt.ecdsa;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Hex;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * 数字签名算法之ECDSA
 * @author shouhua.xiao
 *
 */
public class ECDSATest {
	
	private static final String text = "i love you,qcj";
	
	public static void jdkECDSA(){  
        try {  
            //1.初始化密钥   
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");  
            keyPairGenerator.initialize(256);  
            KeyPair keyPair = keyPairGenerator.generateKeyPair();  
            ECPublicKey ecPublicKey = (ECPublicKey)keyPair.getPublic();  
            ECPrivateKey ecPrivateKey = (ECPrivateKey)keyPair.getPrivate();  
              
              
            //2.执行签名  
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());  
              
            KeyFactory keyFactory = KeyFactory.getInstance("EC");  
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);  
            Signature signature = Signature.getInstance("SHA1withECDSA");  
            signature.initSign(privateKey);  
            signature.update(text.getBytes());  
            byte[] res = signature.sign();  
            System.out.println("签名："+HexBin.encode(res));  
              
            //3.验证签名  
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());  
            keyFactory = KeyFactory.getInstance("EC");  
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);  
            signature = Signature.getInstance("SHA1withECDSA");  
            signature.initVerify(publicKey);  
            signature.update(text.getBytes());  
            boolean bool = signature.verify(res);  
            System.out.println("验证："+bool);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

	public static void main(String[] args) {
		jdkECDSA();
		
		try {
			
			//1.初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
			keyPairGenerator.initialize(256);//长度不要设置错误
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			ECPublicKey ecPublicKey= (ECPublicKey) keyPair.getPublic();//获得公钥
			ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();//获得私钥
			
			//2.生成签名
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("EC");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("SHA1withECDSA");
			signature.initSign(privateKey);
			signature.update(text.getBytes());
			byte[] result = signature.sign();
			System.out.println("jdk EC sign ::: "+Hex.encodeHexString(result));
			
			//3.验证签名
			X509EncodedKeySpec X509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("EC");
			PublicKey publicKey = keyFactory.generatePublic(X509EncodedKeySpec);
			signature = Signature.getInstance("SHA1withECDSA");
			signature.initVerify(publicKey);
			signature.update(text.getBytes());
			boolean flag = signature.verify(result);
			System.out.println("jdk EC verify ::: "+flag);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
		
		
	}

}
