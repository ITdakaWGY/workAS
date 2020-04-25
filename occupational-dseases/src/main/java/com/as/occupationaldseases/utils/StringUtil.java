package com.as.occupationaldseases.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串操作类
 * @author Douxueliang
 *
 */
public class StringUtil {
		
	/**
	 * 判断字符串是否为空 
	 * @param string
	 * @return （true：字符串不为空，false：字符串为空）
	 */
	public static boolean nonNullRequired(String string) {
		boolean flag = false;
		
		if (null==string || ("").equals(string.trim()) || string.length()==0) {
			flag = false;	
		} else {
			flag = true;
		}

		return flag;		
	}
	
	/**
	 * 实现字符集强制转换工作
	 * @param string：需要格式转换的字符串
	 * @param charsetName：字符集名称
	 * @param encoding：字符集编码
	 * @return
	 */
	public static String convert(String string, String charsetName, String encoding) {		
		String returnString = null;
				
		if (StringUtil.nonNullRequired(string) == true) {
			try {
				returnString = new String(string.getBytes(charsetName), encoding);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {		
			returnString = string;			
		}
		
		return returnString;
	}
	
	/**
	 * String转int
	 * @param string
	 * @return
	 */
	public static int stringToInt(String string) {		
		int executeResult = 0;
		
		// 判断是否为null
		if (StringUtil.nonNullRequired(string) == true) {
			// 判断String字符串全部是数字组成
			if(StringUtil.isAllDigit(string) ==true) {				
				// 判断int长度取值范围
				if (string.length() <= 10) {
					executeResult = Integer.parseInt(string);			
				}				
			}		
		}
		
		return executeResult;
	}
	
	/**
	 * 判断String字符串全部是数字组成
	 * @param string
	 * @return（true：字符串全部为数字组成，false：字符串不全部为数字组成）
	 */
	public static boolean isAllDigit(String string) {		
		boolean flag = true;
		
		if (StringUtil.nonNullRequired(string) == true) {
			a : for (int i=string.length(); --i>=0;) {
				// 判断每一位字符是否是数字 
				if (!Character.isDigit(string.charAt(i))) {
					flag = false;
					
					// 跳出a循环
					break a;
				 }			 
			}									
		} else {			
			flag = false;
		}
		
		return flag;		
	}
	
	/**
	 * 判断某个字符串是否存在于数组中
	 * @param source 原数组
	 * @param string 字符串
	 * @return
	 */
	public static boolean contains(String[] source, String string) {
		List<String> list = Arrays.asList(source);
		
		// 利用list的包含方法,进行判断
		if(list.contains(string)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断字符串中是否包含汉字
	 * @param source
	 * @return
	 */
	public static boolean containsChinese(String source) {
		for(int i=source.length(); --i>=0;) {
			String string = source.substring(i, i+1);					
			if(java.util.regex.Pattern.matches("[\u4E00-\u9FA5]", string) == true) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean isEmpty(String string) {
		return (null==string || ("").equals(string.trim()) || string.length()==0);
	}
	
}
