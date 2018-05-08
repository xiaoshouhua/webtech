package com.xsh.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})//注解的作用域
@Inherited//允许被子类继承
@Documented //生成javadoc会包含注解信息
public @interface Description {

	String name();
	
	String description() default "";
}

