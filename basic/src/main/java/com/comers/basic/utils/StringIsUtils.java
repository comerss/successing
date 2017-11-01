package com.comers.basic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String的工具类
 */

public class StringIsUtils {
	
	
	/**
	 * 判断输入参数String是否为空
	 * @param str
	 * @return
	 */
	public static boolean check_str(String... str){
		
		boolean flg = true;
		
		for(int i=0;i<str.length;i++){
			
			if(str[i]!=null && !str[i].equals("")){
				flg = true;
			}else{
				return false;
			}
		}
		return flg;
	}
	
	
	
	/**
	 * 判断返回参数是否为null  如果null则返回""
	 */
	public String return_str(Object str){
		
		Object ch_str = "";
		
		if(null!=str){
			ch_str = str;
		}else{
			ch_str="";
		}
		
		return ch_str.toString();
		
	}
	
	
	
	/**
	 *手机号正则表达式
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles){

		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[7,0-9])|(18[0-3,5-9]))\\d{8}$");

		Matcher m = p.matcher(mobiles);

		return m.matches();

}
	/**
	 * 正则表达式:验证身份证
	 */
	public static final String REGEX_ID_CARD = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
	public static boolean isIDCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}
	/**
	 * 判断是否是银行卡号
	 * @param cardId
	 * @return
	 */
	public static boolean checkBankCard(String cardId) {
		char bit = getBankCardCheckCode(cardId
				.substring(0, cardId.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return cardId.charAt(cardId.length() - 1) == bit;

	}

	private static char getBankCardCheckCode(String nonCheckCodeCardId) {
		if (nonCheckCodeCardId == null
				|| nonCheckCodeCardId.trim().length() == 0
				|| !nonCheckCodeCardId.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeCardId.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

}
