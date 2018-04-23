package com.xsh.encrypt.hmac;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

/**
 * 消息摘要算法之MAC
 * @author shouhua.xiao
 *
 */
public class HmacTest {
	
	private static final String text = "i love you,qcj";

	public static void main(String[] args) {
		  
		jdkHmacMd5();
		
		BCHMacMD5();
	}

	private static void jdkHmacMd5() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");//初始化KeyGenerator
			
			//产生密钥  
			SecretKey secretKey= keyGenerator.generateKey();
			
			//获得密钥 
			//byte[] key = secretKey.getEncoded();
			byte[] key = Hex.decodeHex(new char[]{'a','a','a','a','a','a','a','a','a','a'});
			
			//还原密钥
			SecretKey restoreSecretKey = new SecretKeySpec(key, "HmacMD5");
			
			//实例化Mac
			Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
			
			//初始化Mac
			mac.init(restoreSecretKey);
			
			//执行摘要
			byte[] md5Bytes = mac.doFinal(text.getBytes());
			
			System.out.println("JDK HmacMD5 ::: "+Hex.encodeHexString(md5Bytes));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (DecoderException e) {
			e.printStackTrace();
		}
	}
	
	private static void BCHMacMD5() {
		HMac hMac = new HMac(new MD5Digest());
		
		byte[] key = org.bouncycastle.util.encoders.Hex.decode(new String(new char[]{'a','a','a','a','a','a','a','a','a','a'}));
		
		hMac.init(new KeyParameter(key));
		
		hMac.update(text.getBytes(),0,text.getBytes().length);
		
		byte[] hmacMd5Bytes = new byte[hMac.getMacSize()];
		hMac.doFinal(hmacMd5Bytes, 0);
		
		System.out.println("Bouncy Castle HmacMD5 ::: "+org.bouncycastle.util.encoders.Hex.toHexString(hmacMd5Bytes));
		
	}

}
