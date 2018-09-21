package com.gb.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public abstract class ClassUtil {
	
	/**
	 * 确定class是否可以被加载
	 * @param className 完整类名
	 * @param classLoader 类加载
	 * @return {boolean}
	 */
	public static boolean isPresent(String className, ClassLoader classLoader) {
		try {
			Class.forName(className, true, classLoader);
			return true;
		}
		catch (Throwable ex) {
			// Class or one of its dependencies is not present...
			return false;
		}
	}
	
	/**
	 * 
	 * @Title: getRuntimeInfo
	 * @Description: 获取当前运行的信息
	 * @return
	 */
	public static String getRuntimeInfo() {
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		String text = className + "-" + methodName;
		System.out.println(text);
		return text;
	}
	
	/**
	 * 
	 * @Title: getRuntimeInfo
	 * @Description: 获取当前运行的信息
	 * @return
	 */
	public static Map<String, String> getRuntimeMap() {
		Map<String, String> map = null;
		// 获取第5层方法Controller和Service
		try {
			StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
			StackTraceElement stack = stacks[4];
			final String methodName = stack.getMethodName();
			final String className = stack.getClassName();
			map = new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("class", className);
					put("method", methodName);
				}
			};
			return map;
		} catch (Exception e) {
			map = new HashMap<String, String>();
		}
		return map;
	}
	
	public static String getStackTrace(Throwable t) {
		StringWriter errorsWriter = new StringWriter();  
        t.printStackTrace(new PrintWriter(errorsWriter));  
        return errorsWriter.toString();  
	}
	
	public static void main(String[] args) {
		System.out.println(getRuntimeInfo());
	}
}
