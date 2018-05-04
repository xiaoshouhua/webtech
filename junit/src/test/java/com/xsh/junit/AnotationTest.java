package com.xsh.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AnotationTest {
	
	/*
	 * @Test : 将一个普通方法修饰成为一个测试方法 
	 * @BeforeClass : 它会在【所有的方法运行前】被执行,static修饰 
	 * @AfterClass :它会在【所有的方法运行结束后】被执行,static修饰
	 * @Before :它会在每一个测试方法【被运行前】执行一次
	 * @After : 它会在每一个测试方法【运行后】执行一次
	 * @Ignore : 所修饰的测试方法会被测试运行期忽略
	 * @RunWith: 可以更改测试运行器
	 */

	@Test
    public void testDivide1() {
		assertEquals(3, new Calculate().divide(6, 0));
    }
	
	@Test(expected = ArithmeticException.class)
    public void testDivide2() {
		assertEquals(3, new Calculate().divide(6, 0));
    }
	
	@Test(timeout = 3000,expected = InterruptedException.class)
    public void testExec() throws InterruptedException {
		System.out.println("此处代码会执行...");
		
		Thread.sleep(4000);
		
		System.out.println("此处代码不会会执行...");
    }
}
