### A.分布式锁

#### (一).为什么要用锁？
  	在多线程并发的场景下,使用锁来控制多个任务对同一共享资源的访问，
  	拿到锁的任务优先访问公共资源;
  	
#### (二).分布式锁实现的技术方式

      1.基于数据库实现分布式锁
        特点： 性能较差,容易出现单点故障
              锁没有失效时间,容易死锁 
              非阻塞式的 
              不可重入
        
      2.基于缓存实现分布式锁
        特点： 性能好
              锁失效时间难设置,容易死锁
              非阻塞式的（使用线程等待解决)
              不可重入
            
      3.基于zookeeper实现分布式锁
        特点：实现相对简单 
              可靠性高 
              性能较好 
              可重入