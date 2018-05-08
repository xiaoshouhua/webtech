package com.xsh.annotation;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;

public class DescriptionResolve {

	public static void main(String[] args) {
		
		//1.获取类
		Class clazz = DescriptionTest.class;

		//2.获取DescriptionTest类上的@Description注解
		boolean isExists = clazz.isAnnotationPresent(Description.class);
		if(isExists) {
			Description desc = (Description) clazz.getAnnotation(Description.class);
			System.out.println(desc.name()+":::"+desc.description());
		}
		
		//3.获取方法上的注解
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			
			//第一种方式
			boolean isFlag = method.isAnnotationPresent(Description.class);
			if(isFlag) {
				Description desc = (Description) method.getAnnotation(Description.class);
				System.out.println("①:::"+desc.name()+":::"+desc.description());
			}
			
			//第二种方式
			Annotation[] as = method.getAnnotations();
			for (Annotation annotation : as) {
				if(annotation instanceof Description) {
					Description desc = (Description) annotation;
					System.out.println("②:::"+desc.name()+":::"+desc.description());
				}
			}
		}
		
	}

}
