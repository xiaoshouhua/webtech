

### 加解密算法
    1.安全和密码
    
    2.常用安全体系介绍
    
    3.密码分类及Java的安全组成
    
    4.JDK相关包以及第三方扩展
    
    5.Base64算法及扩展

#### 密码的常用术语
    
	明文 : 待加密信息.
        
	密文 : 经过加密后的明文.
        
	加密 : 明文转为密文的过程.
        
	加密算法 : 明文转为密文的转换算法.
        
	加密秘钥 : 通过加密算法进行加密操作用的秘钥.
        
	 解密 : 将密文转为明文的过程.
        
	解密算法 : 密文转为明文的算法.
        
	解密秘钥 : 通过解密算法进行解密操作用的秘钥.
        
	密码分析 ：捕获密文者试图通过分析截获的密文从而推断出原来的明文或秘钥的过程
        
	主动攻击 : 攻击者非法入侵密码系统，采用伪造、修改、删除等手段向系统注入假消息进行欺骗。（对密文具有破坏作用）
        
	被动攻击：对一个保密系统采取截获密文并对其进行分析和攻击。（对密文没有破坏作用）
        
	密码体制：由明文空间、密文空间、秘钥空间、加密算法和解密算法五部分构成.
        
	密码协议 : 也称安全协议,指以密码学为基础的消息交换的通信协议，目的是在网络环境中提供安全的服务.
        
	密码系统：指用于加密、解密的系统.
        
	柯克霍夫原则：数据的安全基于秘钥而不是算法的保密。即系统的安全取决于秘钥，对秘钥保密，对算法公开.---现代密码学设计的基本原则.

#### 密码分类
    1.时间：
	        古典密码：以字符为基本加密单元.
	        现代密码：以信息块为基本加密单元.
        
    2.保密内容算法:
	        名称              详细说明                     应用领域         类别
	        受限制算法     算法的保密性基于保持算法的秘密      军事领域       古典密码
	        基于秘钥算法   算法的保密性基于对密钥的保密                      现代密码
        
    3.密码体制：
	            名称                  别名                         详细说明
	           对称密码           单钥密码或私钥密码            指加密秘钥与解密密钥相同
	          
	          非对称密码          双钥密码或公钥密码            指加密秘钥与解密密钥不同，密钥分公钥、私钥
	         
	        对称密码算法    单钥密码算法或私钥密码算法           指应用于对称密码的机密、解密算法
	          
	      非对称密码算法    双钥密码算法或公钥密码算法           指对应于非对称密码的加密、解密算法.
        
    4.明文处理方法：
	        分组密码 : 指加密时将明文分为固定长度的组，用同一秘钥和算法对每一块进行加密，输出也是固定长度的密文.多用于网络加密.
	   
	        流密码 : 也称序列密码.指加密时每次加密一位或者一个字节明文.
             
    5.散列函数:是用来验证数据的安全性。
	      特点：长度不受限制
	            哈希值容易计算 
	            
	      相关的算法:
	          1.消息摘要算法MD5等
	          2.SHA--安全散列算法
	          3.MAC--消息认证码算法,苹果操作系统
	          	      
	6. 数字签名：主要针对以数字的形式存储的消息进行的处理.

#### Java安全体系

    1.JCA(Java Cryptography Architecture) -- Java 加密体系结构 
        消息摘要，数字签名
        
    2.JCE(Java Crytography Extension)  -- Java加密扩展包
        DES、AES、RSA算法通过JCE提供 
        
    3.JSSE(Java Secure Socket Extension) --   Java安全套接字扩展包
        JSSE提供基于SSL的加密功能，主要用于网络传输的过程中
        
    4.JAAS(JAVA Authentication and Authentication Service) -- Java鉴别和安全服务
        C:\Java\jdk1.8.0_171\jre\lib\security\java.security中扩展自定义的java安全类
        
        Note: Providers can be dynamically registered instead by calls to
        either the addProvider or insertProviderAt method in the Security class.  

#### 相关Java包、类 
    java.security 
        - 消息摘要
    
    javax.crypto 
        - 安全消息摘要，消息认证（鉴别）码
        
    java.net.ssl 
        - 安全套接字，和网络传输相关的加解密 
        HttpsURLConnection,SSLContext
            
#### 第三方java扩展 
    A.Bouncy Castle 
        - 两种支持方案：
            1）配置：security.provider.[数字]=sun.security.provider.Sun
            2）调用：Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());  
    B.Commons Codec 
        - Apache  
        - Base64、二进制、十六进制、字符集编码 
        - URL编码/解码        
    注意:鱼和熊掌不可兼得,需要根据自己要求灵活选择以上哪种方案.
    
#### 常用算法
	Base64算法
        
	消息摘要算法
        
	 对称加密算法
        
	非对称加密算法 
        
	数字签名算法
        
	数字证书
        
	安全协议
    
#### 1.加密算法之Base64
    ##### 算法实现：
        - Jdk自带 
        - Commons Codec 
        - Bouncy Castle
        
    ##### 应用场景：
    	   e-mail，密钥，证书文件 
    	   
    ##### 产生：邮件的历史问题 
    
    ##### 定义：基于64个字符的编码算法
     
    ##### 关于RFC 2045   
    
    ##### 衍生：
    			Base16、Base32、URLBase 64
    			
#### 2.加密算法之消息摘要算法
    #####分类：
		MD(Message Digest) 
			分类：
			     MD5
				 MD家族（128位摘要信息）
					-MD2、MD4
			区别：
				算法      摘要长度  实现方 
				MD2   128    JDK  
				MD4   128    Bouncy Castle 
				MD5   128    JDK
			特点：长度都是128位
					
		SHA(Secure Hash Algorithm) 
			特点：安全散列算法、  
			分类：
				SHA-1、
				SHA-2（SHA-224、SHA-256、SHA-384、SHA-512)
			
			区别：
				 算法                   摘要长度         实现方 
				SHA-1	   160     JDK
				SHA-224	   224     Bouncy Castle	
				SHA-256	   256     JDK
				SHA-384	   384     JDK
				SHA-512	   512     JDK
			应用：
				1.加入约定key  
				2.增加时间戳 
				3.排序 
				
				如：http://host:port/**?msg=1232o23asdlaskasjdpoew&timestamp=12322325324 
				    msg:原始消息+key+时间戳	
		
		MAC(Message Authentication Code)
			特点：融合MD、SHA 
			
			分类：
				MAC(Message Authentication Code) 
				HMAC(keyed-Hash Message Authentication Code),含有密钥的散列函数算法
				  -MD系列：HmacMD2、HmacMD4、HmacMD5
				  -sha系列：HmacSHA1、HmacSHA-224、HmacSHA-256、HmacSHA-384、HmacSHA-512 
			
			区别：
				 算法                                摘要长度         实现方
				HmacMD2		   128      Bouncy Castle	
				HmacMD4         128      Bouncy Castle	
				HmacMD5         128      JDK
				HmacSHA1	       160      JDK
				HmacSHA224	   224      Bouncy Castle	
				HmacSHA256	   256      JDK
				HmacSHA384	   384      JDK
				HmacSHA512	   512      JDK
			
			其他实现算法：(均采用Bouncy Castle实现)
				RipeMD 
				Tiger 
				Whirlpool 
				GOST3411
				
		
		 
		验证数据完整性 
		
		数字签名核心算法