package com.sunrise.base;

import java.net.URL;

/**
 * 
 * @author guocf
 * 操作系统检测
 */
public class OSChecker {
	public static final String CLASS_PATH;
	public static final String WEB_INF_PATH;
	// public static final boolean isLinux;
	public static final boolean isWindows;
	static {
		URL resource = OSChecker.class.getResource("OSChecker.class");
		String classPath = resource.getPath();
		String className = OSChecker.class.getName().replace('.', '/')+ ".class";
		String classesPath = classPath.substring(0, classPath.indexOf(className));
		// System.getProperty("os.name").toUpperCase().equals("LINUX"))
		if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1
				&& classesPath.startsWith("/")) {
			classesPath = classesPath.substring(1);
			// isLinux = false;
			isWindows = true;
		} else {
			// isLinux = true;
			isWindows = false;
		}
		CLASS_PATH = classesPath;
		WEB_INF_PATH=CLASS_PATH.substring(0,CLASS_PATH.length()-"classes/".length());
	}

	public static void main(String arg[]) {
		System.out.println(OSChecker.CLASS_PATH);
		System.out.println("WEB_INF_PATH==>"+OSChecker.WEB_INF_PATH);
		System.out.println(OSChecker.isWindows);
	}
}
