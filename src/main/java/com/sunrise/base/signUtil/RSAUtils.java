package com.sunrise.base.signUtil;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
  
/** *//** 
 * <p> 
 * RSA公钥/私钥/签名工具包 
 * </p> 
 * <p> 
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/> 
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/> 
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全 
 * </p> 
 */  
public class RSAUtils {  
  
    /** *//** 
     * 加密算法SHA1WithRSA
     */  
    public static final String KEY_ALGORITHM = "RSA";  
      
    /** *//** 
     * 签名算法 
     */  
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";  
  
    /** *//** 
     * 获取公钥的key 
     */  
    private static final String PUBLIC_KEY = "RSAPublicKey";  
      
    /** *//** 
     * 获取私钥的key 
     */  
    private static final String PRIVATE_KEY = "RSAPrivateKey";  
      
    /** *//** 
     * RSA最大加密明文大小 
     */  
    private static final int MAX_ENCRYPT_BLOCK = 117;  
      
    /** *//** 
     * RSA最大解密密文大小 
     */  
    private static final int MAX_DECRYPT_BLOCK = 128;   
  
    /** *//** 
     * <p> 
     * 生成密钥对(公钥和私钥) 
     * </p> 
     *  
     * @return 
     * @throws Exception 
     */  
    public static Map<String, Object> genKeyPair() throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);  
        keyPairGen.initialize(1024);  //这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        Map<String, Object> keyMap = new HashMap<String, Object>(2);  
        keyMap.put(PUBLIC_KEY, publicKey);  
        keyMap.put(PRIVATE_KEY, privateKey);  
        return keyMap;  
    }  
      
    /** *//** 
     * <p> 
     * 用私钥对信息生成数字签名 
     * </p> 
     *  
     * @param data 已加密数据 
     * @param privateKey 私钥(BASE64编码) 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String sign(byte[] data, String privateKey) throws Exception {  
        byte[] keyBytes = BASE64.decode(privateKey.getBytes());  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initSign(privateK);  
        signature.update(data);  
        return new String(BASE64.encode(signature.sign()));  
        
//        byte[] keyBytes = BASE64.decode(privateKey.getBytes());
//		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
//		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
//		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//		signature.initSign(privateK);
//		signature.update(data);
//		return new String(BASE64.encode(signature.sign()));
        
    }  
  
    /** *//** 
     * <p> 
     * 校验数字签名 
     * </p> 
     *  
     * @param data 已加密数据 
     * @param publicKey 公钥(BASE64编码) 
     * @param sign 数字签名 
     *  
     * @return 
     * @throws Exception 
     *  
     */  
    public static boolean verify(byte[] data, String publicKey, String sign)  
            throws Exception {  
//        byte[] keyBytes = BASE64.decode(publicKey.getBytes());  
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
//        PublicKey publicK = keyFactory.generatePublic(keySpec);  
//        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
//        signature.initVerify(publicK);  
//        signature.update(data);  
//        return signature.verify(BASE64.decode(sign).getBytes());  
        
        byte[] keyBytes = BASE64.decode(publicKey.getBytes());
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(BASE64.decode(sign.getBytes()));
        
    }  
  
    /** *//** 
     * <P> 
     * 私钥解密 
     * </p> 
     *  
     * @param encryptedData 已加密数据 
     * @param privateKey 私钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)  
            throws Exception {  
        byte[] keyBytes = BASE64.decode(privateKey.getBytes());  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, privateK);  
        int inputLen = encryptedData.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段解密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return decryptedData;  
    }  
  
    /** *//** 
     * <p> 
     * 公钥解密 
     * </p> 
     *  
     * @param encryptedData 已加密数据 
     * @param bs 公钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)  
            throws Exception {  
        byte[] keyBytes = BASE64.decode(publicKey.getBytes());  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key publicK = keyFactory.generatePublic(x509KeySpec);  		
        
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, publicK);  
        int inputLen = encryptedData.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段解密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return decryptedData;  
    }  
  
    /** *//** 
     * <p> 
     * 公钥加密 
     * </p> 
     *  
     * @param data 源数据 
     * @param publicKey 公钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)  
            throws Exception {  
        byte[] keyBytes = BASE64.decode(publicKey.getBytes());  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key publicK = keyFactory.generatePublic(x509KeySpec);  

        
        // 对数据加密  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, publicK);  
        int inputLen = data.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段加密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(data, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_ENCRYPT_BLOCK;  
        }  
        byte[] encryptedData = out.toByteArray();  
        out.close();  
        return encryptedData;  
    }  
  
    /** *//** 
     * <p> 
     * 私钥加密 
     * </p> 
     *  
     * @param data 源数据 
     * @param privateKey 私钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)  
            throws Exception {  
        byte[] keyBytes = BASE64.decode(privateKey.getBytes());
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, privateK);  
        int inputLen = data.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段加密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(data, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_ENCRYPT_BLOCK;  
        }  
        byte[] encryptedData = out.toByteArray();  
        out.close();  
        return encryptedData;  
    }  
  
    /** *//** 
     * <p> 
     * 获取私钥 
     * </p> 
     *  
     * @param keyMap 密钥对 
     * @return 
     * @throws Exception 
     */  
    public static String getPrivateKey(Map<String, Object> keyMap)  
            throws Exception {  
        Key key = (Key) keyMap.get(PRIVATE_KEY);  
        return new String(BASE64.encode(key.getEncoded()));  
    }  
  
    /** *//** 
     * <p> 
     * 获取公钥 
     * </p> 
     *  
     * @param keyMap 密钥对 
     * @return 
     * @throws Exception 
     */  
    public static String getPublicKey(Map<String, Object> keyMap)  
            throws Exception {  
        Key key = (Key) keyMap.get(PUBLIC_KEY);  
        return new String(BASE64.encode(key.getEncoded()));  
    }  
  
    public static void main(String[] a)throws Exception {  
    	Map params = genKeyPair();
    	String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9sZ2VmYdE/heNqdYYBPDRc5Z9AfkIq93GmY8WjL2Jj3ZFcJUHjR7ugxXvtzLn5z8Hdq3eyyD7E2evcpU1qQVRYRdOkNLRKYq9b6HQ4pTJrAN/fvl1DrHmTaW++k4Rcu9e0SGZvYP+7qKw0tvtlhR3Y0fzz089VUC9w1pFYO0ASwIDAQAB";
    	String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL2xnZWZh0T+F42p1hgE8NFzln0B+Qir3caZjxaMvYmPdkVwlQeNHu6DFe+3MufnPwd2rd7LIPsTZ69ylTWpBVFhF06Q0tEpir1vodDilMmsA39++XUOseZNpb76ThFy717RIZm9g/7uorDS2+2WFHdjR/PPTz1VQL3DWkVg7QBLAgMBAAECgYEAlojlLVrw5edeeaVVqSoCkbTQr9No9WCuVnR2AMcfgpK+oMx+98cdzNlqBxmy5CW29w1PXYaFXbpAhhLAJh1DgbgygxMSfCs4i/iwkn7jWBnEjyChS17LQqOJZdoGpjfv0h0+nrIwqt0pMD81tp9WGdx0BBKMCE1gnHtsPxwWW8ECQQD8fvL/T4744usVCvhfhYxt+H9fBBUG+bPkdGna5F9qT2mdeBJpyz8KT+9MTxY4xfU8Sn3ItD8RbVBePbiGIam/AkEAwFOMGD0hC2trQTUDhBgQpAKv7K9/dCzAGdM1/y3WvdCyyN0shB79pe18sJ3S9/ZfkLthmGx5Hn2SqqaFLJiUdQJBAJNOFMHyTjzRVciO/9vqc8TshpKONR4+qz6K0/7J4QiKj9k4ZbWmx35ip/7i3Nn+U1X7N2rSDUFDfvGcHHzUQs0CQGAS3C7XioRL2r4uH12DL/zeIseXO5HrMOM1sQmR+m3DrSvN4Ij3ejnDEsCfdl3Lwx1nnW8o8LAchoGcwXXvmmkCQFCcXBz9Q4zgncf0NqhqYEfc8Fyoa5Hg3OCQ6xm8v7nHoHm+R4q2IcWR87hzUbJgqE7m2+AACyYiw6eY7tXK8Q0=";
    	String s= "appKey=11000021&passLength=6&passMode=0&phone_no=13981188888&timeStamp=20141208154515&userName=sunrise";
    	String sign = "NrGDwaqVF/JRuSiyrIijA82Wu++vbqo9w6px/FghL6crvkmwDJIMeP7ZhOm692dX+fuyTXgiLBfTrEPWKGBLw3Nri1J8LnjmY5bfAeEpr2iao8YJbScrV6qpoNW5yOzxPu6Ot82dQGeWjlyB438H5jvsNY5F5wDriLZSiWTyI20=";
    	//私钥加密 
//    	byte[] b = encryptByPrivateKey(s.getBytes(), privateKey);	
//    	System.out.println("私钥:"+privateKey);
    	//公钥解密 
    	
//    	s=new String(BASE64.encode(RSAUtils.encryptByPrivateKey(sign.getBytes("UTF-8"), publicKey)));
//    	s=new String(RSAUtils.decryptByPublicKey(BASE64.encode(sign.getBytes("UTF-8")), publicKey));
//    	byte[] data = URLDecoder.decode(sign, "UTF-8").getBytes("UTF-8");
////    	byte[] data =  URLDecoder.decode(sign, ENCODING).getBytes(ENCODING);
//    	byte[] data2  = BASE64.decode(sign.getBytes());
    	
    	String md5str = MD5.MD5Encode(URLEncoder.encode(s, "UTF-8"));
//    	
    	Boolean res = verify(md5str.getBytes(),publicKey,sign);
//    	s=new String(RSAUtils.decryptByPublicKey(data2, publicKey));
////    	s=new String(decryptByPublicKey(BASE64.decode(sign.getBytes()),  publicKey));
    	System.out.println("公钥:"+publicKey);
    	System.out.println("结果为:"+res);
    	
    	md5str = MD5.MD5Encode(URLEncoder.encode(s, "UTF-8"));
    	
    	String sign_result = sign(md5str.getBytes(),privateKey);
    	
    	sign_result = URLEncoder.encode(sign_result,"UTF-8");
    	
    	System.out.println("加密后为:"+sign_result);
//    	//公钥加密 
//    	b = encryptByPublicKey(s.getBytes(), publicKey);
//    	System.out.println("公钥:"+publicKey);
//    	//私钥解密 
//    	s=new String(decryptByPrivateKey(b,  privateKey));
//    	System.out.println("私钥:"+privateKey);
//    	System.out.println(s); 
    	
    }
}  