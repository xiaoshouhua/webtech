#### Quartz
	OpenSymphony提供的强大的开源任务调度框架.
	官网地址:
	特点：
       强大的调度功能
       灵活的应用方式
       分布式和集群能力
		
	涉及模式:
       1.Builder模式 
       2.Factory模式 
       3.组件模式
		
	三个核心概念：
       调度器
       任务
       触发器
		
	重要组成：
       Job : 有且仅有一个方法execute,类似TimerTask中的run方法
             每次调度器执行Job时,它在调用execute方法前会创建一个新的Job实例，
             当调用完成后，关联的Job对象实例会被释放，释放的实例会被垃圾回收机制回收
			
       JobDetail:
             JobDetail为Job实例提供了许多设置属性，以及JobDataMap成员变量属性，
             它用来存储特定Job实例的状态信息，调度器需要借助JobDetail对象来添加Job实例
			
             重要属性:
                name(任务名)
                group(任务分组)
                jobClass(任务实现类)
                jobDataMap
                    是什么?
                      1.在进行任务调度时JobDataMap存储在JobExecutionContext中，非常方便获取
                      2.JobDataMap可以用来装载任何可序列化的数据对象 
                      3.JobDataMap实现了JDK的Map接口，并且添加了一些非常方便的方法用来存取基本数据类型.
				    获取方式:
                      1.从Map中直接获取 
                      2.Job实现类中添加setter方法对应JobDataMap的键值
                      (Quartz框架默认的JobFactory实现类在初始化Job实例对象时会自动地调用这些setter方法)
				   	
       JobExecutionContext:
             1.当Scheduler调用一个Job，就会将JobExecutionContext传递给Job的execute()方法 
             2.Job能通过JobExecutionContext对象访问到Quartz运行时候的环境以及Job本身的明细数据
			
       JobBuilder 
       JobStore
			
       Trigger：
             概述：
                Quartz中的触发器用来告诉调度程序作业什么时候出发，即Trigger对象是用来出发执行Job的.
				
             通用属性：
                JobKey:表示Job实例的标识，触发器被触发时，该指定的job实例会执行.
                StartTime：标识触发器的时间表首次被触发的时间，它的值的类型是java.util.Date
                EndTime:指定触发器的不再被触发的时间，它的值的类型是java.util.Date
             分类：
                SimpleTrigger：
                    概述：
                        在一个指定时间段内执行执行一次作业任务，或是在指定的时间间隔内多次执行作业任务
						
                    需要注意的点：
                        1.重复次数可以为0，正整数或是SimpleTrigger.REPEAT_INDEFINITELY常量值 
                        2.重复执行间隔必须为0或长整数 
                        3.一旦被指定了endTime参数，那么它会覆盖重复次数参数的效果。	
                CronTrigger:
                    概述：
                        基于日历的作用调度器，而不是像SimpleTrigger那样精确指定间隔时间，比SimpleTrigger更常用。
                    Cron表达式：
                        用于配置CronTrigger实例，是由7个表达式组成的字符串，描述了时间表的详细信息.
                    格式：
                        [秒][分][小时][日][月][周][年]		
					
       TriggerBuilder 
       ThreadPool	
       Scheduler:
                描述：所有的Scheduler实例应该由SchedulerFactory来创建
                常用函数：Date scheduleJob(JobDetail jobDetail,Trigger trigger);--返回最近一次即将要执行的时间
       StdSchedulerFactory: 
                1.使用一组参数（java.util.Properties)来创建和初始化Quartz调度器 
                2.配置参数一般存储在quartz.properties中 
                3.调用getScheduler方法就能创建和初始化调度器对象
						
       Calendar - 一个Trigger可以和多个Calendar管理，以排除或包含某些时间点
       监听器：
                JobListener,
                TriggerListener,
                SchedulerListener		
			
##### quartz.properties
	默认情况下加载工程目录下的quartz.properties,如果不存在,则加载quartz jar包下的/org/quartz/quartz.properties
	    
	1.调度器属性：
	    org.quartz.scheduler.instanceName属性用来区分特定的调度器实例，可以按照功能用途来给调度器命名。
	    org.quartz。scheduler.instanceId属性和前者一样，也允许任何字符串，但这个值必须是在所有调度器实例中是唯一的，尤其是在一个集群当中，作为集群的唯一key。加入你想quartz帮你生成这个值的话，可以设置为Auto.
		
	2.线程池属性：
	    threadCount:指定线程数，至少为1（无默认值）（一般设置为1-100之间的整数合适）
		
	    threadPriority:线程的优先级 
		
	    org.quartz.threadPool.class：线程的实现类（一般使用SimpleThreadPool即可满足几乎所有用户的需求）
		
	3.作业存储设置：
	    描述了在调度器实例的声明周期中，Job和Trigger信息是如何被存储的
			
	4.插件配置：
	    满足特定需求用到的插件.		
		
##### Quartz和Spring整合方式
	1.MethodInvokingJobDetailFactoryBean
	2.JobDetailFactoryBean