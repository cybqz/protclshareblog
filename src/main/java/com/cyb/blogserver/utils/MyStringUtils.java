package com.cyb.blogserver.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MyStringUtils {
	
	public static String formatDate(String format, Date date) {
		
		if(StringUtils.isBlank(format)) {
			format = "yyyyMMDDHHmmss";
		}
		if(date == null) {
			date = new Date();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 生成随机ID
	 * @return
	 */
	public static String getPrimaryKey(){
		return UUID.randomUUID().toString();
	}
}
