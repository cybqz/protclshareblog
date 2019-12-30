package com.cyb.blogserver.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MyUtils {
	
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

	public static LocalDate getLocalDate(){

		return LocalDate.now();
	}

	/**
	 * 根据文件名称获取文件类型
	 * @param fullName
	 * @return
	 */
	public static String getFileTypeByFullName(String fullName){
		if(!StringUtils.isEmpty(fullName)){
			return fullName.substring(fullName.lastIndexOf("."), fullName.length());
		}
		return null;
	}
}
