package com.lenovo.cloudbuild.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 时间戳转化为日期格式
	 * @param timestap
	 * @return
	 */
	public static String dateFormat(Long timestap) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(timestap)); 
	}
	

}
