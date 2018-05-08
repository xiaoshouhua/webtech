package com.xsh.annotation;

@Description(name="注解测试类",description="这是一个注解类@Description的测试类")
public class DescriptionTest {

	private String name;

	@Description(name="注解测试类的测试方法")
	public static void test() {
	   System.out.println("i am China ...");	
	}
	
	public static void main(String[] args) {
		test();
	}

}
