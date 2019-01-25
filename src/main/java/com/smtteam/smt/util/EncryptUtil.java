package com.smtteam.smt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
 * @author zzy 
 * @version 创建时间：2016-9-18 上午9:15:32 
 * 类说明 加密算法工具类
 */
public class EncryptUtil {
	private EncryptUtil(){}
	private final static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);
	 /**
	  * 
	  * @Title: SHA256
	  * @author: zzy
	  * @date: 2016-9-18 上午9:17:29
	  * @Description: 传入文本内容，返回 SHA-256 串  
	  * @param strText
	  * @return
	  * @return: String
	  */
	 public static String SHA256(final String strText){  
	   return SHA(strText, "SHA-256");  
	 }
	  
	  /**
	   *  
	   * @Title: SHA
	   * @author: zzy
	   * @date: 2016-9-18 上午9:18:33
	   * @Description: 字符串 SHA 加密 
	   * @param strText
	   * @param strType
	   * @return
	   * @return: String
	   */
	  private static String SHA(final String strText, final String strType){  
	    // 返回值  
	    String strResult = "";
	    // 是否是有效字符串  
	    if (strText != null && strText.length() > 0){  
	      try{  
	        // SHA 加密开始  
	        // 创建加密对象 并傳入加密類型  
	        MessageDigest messageDigest = MessageDigest.getInstance(strType);  
	        // 传入要加密的字符串  
	        messageDigest.update(strText.getBytes());  
	        // 得到 byte 類型结果  
	        byte byteBuffer[] = messageDigest.digest();  
	  
	        // 將 byte 轉換爲 string  
	        StringBuilder strHexString = new StringBuilder();
	        // 遍歷 byte buffer  
	        for (int i = 0; i < byteBuffer.length; i++){  
	          String hex = Integer.toHexString(0xff & byteBuffer[i]);  
	          if (hex.length() == 1){
	            strHexString.append('0');  
	          }  
	          strHexString.append(hex);  
	        }  
	        // 得到返回結果  
	        strResult = strHexString.toString();  
	      }catch (NoSuchAlgorithmException e){
	      	logger.error("NoSuchAlgorithmException !");
	      }
	    }  
	    return strResult;  
	  }
}
