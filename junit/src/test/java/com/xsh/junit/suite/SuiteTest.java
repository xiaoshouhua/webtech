package com.xsh.junit.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试套件入口类
 * @author shouhua.xiao
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value = { Task1.class,Task2.class,Task3.class })
public class SuiteTest {

	/*
	 *1.测试套件就是组织测试类一起运行的 
	 *   实现步骤：
	 *   写一个作为测试套件的入口类,这个类里不包含其他的方法 
	 *   更改测试运行器Suite.class  
	 *   将要测试的类作为数组传入到Suite.SuiteClasses({})中
	 */
}
