package com.xsh.encrypt.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 消息摘要算法之SHA算法
 * @author shouhua.xiao
 *
 */
public class SHATest {
	
	private static final String text = "i love you,qcj";

	public static void main(String[] args) {
		
		jdkSha1();
		BCSha1();
		CCSha1();
		
		BCSha224();
	}

	private static void jdkSha1() {
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA");// 这里的SHA代表的是sha-1

			sha.update(text.getBytes());

			System.out.println("JDK SHA-1 ::: " + Hex.encodeHexString(sha.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private static void BCSha1() {
		Digest sha1Digest = new SHA1Digest();
		sha1Digest.update(text.getBytes(), 0, text.getBytes().length);
		byte[] shaBytes = new byte[sha1Digest.getDigestSize()];
		sha1Digest.doFinal(shaBytes, 0);
		
		System.out.println("Bouncy Castle SHA-1 ::: " + Hex.encodeHexString(shaBytes));
	}
	
	private static void BCSha224() {
		
		//method 1
		Digest sha224Digest = new SHA224Digest();
		sha224Digest.update(text.getBytes(), 0, text.getBytes().length);
		byte[] shaBytes = new byte[sha224Digest.getDigestSize()];
		sha224Digest.doFinal(shaBytes, 0);
		System.out.println("Bouncy Castle SHA-224 ::: " + Hex.encodeHexString(shaBytes));
		
		//method 2
		Security.addProvider(new BouncyCastleProvider());
		MessageDigest sha224;
		try {
			sha224 = MessageDigest.getInstance("SHA-224");
			sha224.update(text.getBytes());
			System.out.println("Bouncy Castle SHA-224 ::: " + Hex.encodeHexString(sha224.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}// 这里的SHA代表的是sha-1

	}
	
	private static void CCSha1() {
		System.out.println("Commons Codec SHA-1 ::: " +DigestUtils.sha1Hex(text.getBytes()));
	}
}
