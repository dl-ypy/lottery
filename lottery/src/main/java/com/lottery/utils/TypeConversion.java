package com.lottery.utils;
/**
 * 类型转换的封装类
 * @author Administrator
 */
public class TypeConversion {
	public static Integer StrToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
