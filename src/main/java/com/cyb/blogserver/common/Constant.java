package com.cyb.blogserver.common;

import java.util.HashSet;
import java.util.Set;

public class Constant {

	public static String SESSION_NAME = "SESSION_NAME";
	
	public static String PROJECT_NAME = "tecshareblog";
	
	public static String ROLE_ADMIN = "admin";

	
	/***
	 * reading modals
	 */
	public static Set<String> READING_MODALS = new HashSet<String>();
	static{
		READING_MODALS.add("bk");
		READING_MODALS.add("me");
		READING_MODALS.add("mc");
	}
	
	/***
	 * modals
	 */
	public static Set<String> MODALS = new HashSet<String>();
	static{
	}
}
