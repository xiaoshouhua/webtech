package com.xsh.encrypt.elgamal;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.spec.DHParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 非对称加密之ElGama
 * @author shouhua.xiao
 */
public class ElGamalTest {
	
	public static void bcElGamal() {  
        try {  
            // 只支持公钥加密，私钥解密（BC实现）  
            Security.addProvider(new BouncyCastleProvider());  
  
            // 1.初始化密钥  
            AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator  
                    .getInstance("ELGamal");  
//          KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("ELGamal");   
              
//          System.out.println(algorithmParameterGenerator);  
//          System.out.println(senderKeyPairGenerator);  
              
            algorithmParameterGenerator.init(256);  
            AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();  
            DHParameterSpec dhParameterSpec = algorithmParameters.getParameterSpec(DHParameterSpec.class);  
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ElGamal"); //使用ElGamal非对称加密方式  
            keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());  
            KeyPair keyPair = keyPairGenerator.generateKeyPair();  
            PublicKey eLGamalPublicKey = keyPair.getPublic();  
            PrivateKey eLGamalPrivateKey = keyPair.getPrivate();  
              
            System.out.println("ELGamalPublicKey:" + Base64.encodeBase64String(eLGamalPublicKey.getEncoded()));  
            System.out.println("ELGamalPrivateKey:" + Base64.encodeBase64String(eLGamalPrivateKey.getEncoded()));  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

	public static void main(String[] args) {
		bcElGamal();
	}

}
