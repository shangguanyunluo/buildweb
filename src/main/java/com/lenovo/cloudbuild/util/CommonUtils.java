package com.lenovo.cloudbuild.util;

public class CommonUtils {

	public static boolean isNull(String directoryName) {
		if ("".equals(directoryName) || directoryName == null) {

			return true;
		}
		return false;
	}

}
