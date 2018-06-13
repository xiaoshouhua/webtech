#### 什么是定时任务调度？
	基于给定的时间点，给定的时间间隔或者给定的执行次数自动执行的任务.
	
#### Timer和Quartz的区别
	1.出身不同
	2.底层机制
	
#### Timer
	定义：
		有且仅有一个后台线程对多个业务线程进行【定时定频率】的调度.
		
##### schedule的四种用法
	1.schedule(TimerTask task, Date time) - 在时间等于或超过time的时候执行且仅执行一次task
		
	2.schedule(TimerTask task, long delay, long period) - 时间等于或超过time时首次执行task，
		之后每隔period毫秒重复执行一次task
	    
	3.schedule(TimerTask task, long delay) - 等待delay毫秒后执行且仅执行一次task
	    
	4.schedule(TimerTask task, long delay, long period) - 等待delay毫秒后首次执行task，
		之后每隔period毫秒重复执行一次task
		
##### scheduleAtFixedRate的两种用法
	1.scheduleAtFixedRate(TimerTask task,Date firstTime,long period) -- 时间等于或超过time时首次执行task，之后每隔period毫秒重复执行一次task
		
	2.scheduleAtFixedRate(TimerTask task,long delay,long period) -- 等待delay毫秒后首次执行task，
		之后每隔period毫秒重复执行一次task
		
##### schedule和scheduleAtFixedRate的区别
	1.首次计划执行时间早于当前时间 
	
       1.1.schedule方法
           "fixed-delay":如果第一次执行时间被delay了，随后的执行时间按照上一次实际执行完成的时间点进行计算.
       1.2 scheduleAtFixedRate()方法
           "fixed-rate":如果第一次执行时间被delay了，随后的执行时间按照上一次开始的时间点进行计算，并且为了赶上进度会多次执行任务，因此TimerTask中的执行体需要考虑同步.
			
	2.任务执行所需时间超出任务的执行周期间隔
	
       2.1 schedule方法
           下一次执行时间相对于上一次实际执行完成的时间点，因此时间会不断延后
			
       2.2 scheduleAtFixedRate()方法
           下一次执行时间相对于上一次开始的时间点，因此执行时间一般不会延后，因此存在并发性.
			
##### Timer的不足

	1.管理并发任务的缺陷
       Timer有且仅有一个线程去执行定时任务，如果存在多个任务，且任务时间过长，会导致执行效果与预期不符.
		
	2.当任务抛出异常时的缺陷
       如果TimerTask抛出RuntimeException，Timer会停止所有任务的运行