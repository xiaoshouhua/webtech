
#### 什么是Memcache？
    1.Memcached是高性能的，分布式的内存对象缓存系统，用于在动态应用中减少数据库负载，提升访问速度。
        
    2.Memcached由Danga Interactive开发，用于提升LiveJournal.com访问速度的。LJ每秒动态页面访问量几千次，用户700万.

#### 为什么会有Memcache和memcached两种名称
    Memcache是项目名称,memcached是主程序文件名
 
#### Memcache能解决什么问题？
    利用Memcached将数据库负载大幅度降低，更好的分配资源，更快速访问。

#### Memcache适用于什么场景？
    
    1.非持久化存储 : 对数据存储要求不高
        
    2.分布式存储: 不适合单机使用
        
    3.Key/Value存储 : 格式简单,不支持List,Array数据格式

#### Memcache的安装

    memcache服务器端的安装:
        
        所谓服务器端的安装就是在服务器（一般都是linux系统）上安装Memcache实现数据的存储.
        
        步骤:
        
            1.编译安装 libevent Memcache
            
            2.使用以来管理工具yum、apt-get
        
    memcached客户端的安装:
            
        所谓客户端的安装就是指java（或者其他程序，Memcacvahe还有其他不错的api接口提供）去使用服务器端的Memcache提供的函数，需要添加扩展

#### Memcache客户端API
     
            
        
#### 注意事项:
     
     1.不要在单机模式中使用Memcached
     
     2.不要只使用Memcache保存重要数据
     
     3.定期查看缓存的分布状况和击中情况        