### Java自定义注解
	概念：
        Java提供了一种原程序中的元素关联任何信息和任何元数据的途径和方法.
		
### Java中的常见注解
    1.JDK自带注解
      @Override 表示当前方法覆盖了父类的方法
      @Deprecation 表示方法已经过时,方法上有横线，使用时会有警告。
      @SuppviseWarnings 表示关闭一些警告信息(通知java编译器忽略特定的编译警告)
      
    2.常用第三方注解：
        
      Spring：
          @Autowired
          @Service
          @Repository
        
      Mybatis：
          @InsertProvider
          @UpdateProvider
          @Options  

### 注解的分类
	按运行机制分：
        1.源码注解 -- 注解只在源码中存在，编译成.class文件就不存在了.
        2.编译时注解 -- 注解在源码和.class文件中都存在
        3.运行时注解 -- 在运行阶段还起作用,甚至会影响运行逻辑的注解
	        
	按来源分：
        1.来自JDK的注解
        2.来自第三方的注解
        3.我们自己自定义的注解
        
    元注解
    
### 自定义注解
    自定义注解的语法要求      
        
        1.使用@interface关键字定义注解
        
        2.成员以无参无异常方式声明
        
        3.可以用default为成员指定一个默认值
        
        4.成员类型是受限的，合法的类型包含原始类型以及String，Class，Annotation，Enumeration
        
        5.如果注解只有一个成员，则成员名必须命名为value(),在使用的时候可以忽略成员名和赋值号（=）   
        
        6.注解类可以没有成员，没有成员的注解称为标识注解   
        
    注解的作用范围@Target：
        
        CONSTRUCTOR 构造方法声明      
        
        FIELD 字段声明
		
        LOCAL_VARIABLE 局部变量声明
		
        METHOD    方法声明
		
        PACKAGE   包声明
		    
        PARAMETER 参数声明
		
        TYPE 类,接口        
		
	注解的生命周期@Retention：
	    
        SOURCE  -- 注解只在源码中存在，编译成.class文件就不存在了.
        
        CLASS   -- 注解在源码和.class文件中都存在
            
        RUNTIME -- 在运行阶段还起作用,甚至会影响运行逻辑的注解
        
	其他注解：
	    
        @Inherited //允许被子类继承
            
        @Documented //生成javadoc会包含注解信息
