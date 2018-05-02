package com.xsh.jedis.client.commands;

public enum Command {
	
	/****************************** 关于Connection（连接）相关命令 ******************************/
	AUTH,
	ECHO,
	PING,
	QUIT,
	SELECT,
	UNLINK,
	TOUCH,
	
	/****************************** 关于Key【键】相关命令 ******************************/
	DEL,
	DUMP,
	EXISTS,
	EXPIRE,
	EXPIREAT,
	KEYS,
	MIGRATE,
	MOVE,
	OBJECT,
	PERSIST,
	PEXPIRE,
	PEXPIREAT,
	PTTL,
	RANDOMKEY,
	RENAME,
	RENAMENX,
	RESTORE,
	SORT,
	TTL,
	TYPE,
	SCAN,
	
	/****************************** 关于String（字符串）相关命令 ******************************/
	APPEND,
	BITCOUNT,
	BITOP,
	DECR,
	DECRBY,
	GET,
	GETBIT,
	GETRANGE,
	GETSET,
	INCR,
	INCRBY,
	INCRBYFLOAT,
	MGET,
	MSET,
	MSETNX,
	PSETEX,
	SET,
	SETBIT,
	SETEX,
	SETNX,
	SETRANGE,
	STRLEN,
	
	/****************************** 关于Hash（哈希表）相关命令 ******************************/
	HDEL,
	HEXISTS,
	HGET,
	HGETALL,
	HINCRBY,
	HINCRBYFLOAT,
	HKEYS,
	HLEN,
	HMGET,
	HMSET,
	HSET,
	HSETNX,
	HVALS,
	HSCAN,
	
	/****************************** 关于List（列表）相关命令 ******************************/
	BLPOP,
	BRPOP,
	BRPOPLPUSH,
	LINDEX,
	LINSERT,
	LLEN,
	LPOP,
	LPUSH,
	LPUSHX,
	LRANGE,
	LREM,
	LSET,
	LTRIM,
	RPOP,
	RPOPLPUSH,
	RPUSH,
	RPUSHX,
	
	/****************************** 关于Set（集合）相关命令 ******************************/
	SADD,
	SCARD,
	SDIFF,
	SDIFFSTORE,
	SINTER,
	SINTERSTORE,
	SISMEMBER,
	SMEMBERS,
	SMOVE,
	SPOP,
	SRANDMEMBER,
	SREM,
	SUNION,
	SUNIONSTORE,
	SSCAN,

	/****************************** 关于SortedSet（有序集合）相关命令 ******************************/
	ZADD,
	ZCARD,
	ZCOUNT,
	ZINCRBY,
	ZRANGE,
	ZRANGEBYSCORE,
	ZRANK,
	ZREM,
	ZREMRANGEBYRANK,
	ZREMRANGEBYSCORE,
	ZREVRANGE,
	ZREVRANGEBYSCORE,
	ZREVRANK,
	ZSCORE,
	ZUNIONSTORE,
	ZINTERSTORE,
	ZSCAN,

	/****************************** 关于Pub/Sub（发布/订阅）相关命令 ******************************/
	PSUBSCRIBE,
	PUBLISH,
	PUBSUB,
	PUNSUBSCRIBE,
	SUBSCRIBE,
	UNSUBSCRIBE,

	/****************************** 关于Transaction（事务）相关命令 ******************************/
	DISCARD,
	EXEC,
	MULTI,
	UNWATCH,
	WATCH,

	/****************************** 关于Script（脚本）相关命令 ******************************/
	EVAL,
	EVALSHA,
//	SCRIPT EXISTS
//	SCRIPT FLUSH
//	SCRIPT KILL
//	SCRIPT LOAD

	/****************************** 关于Server（服务器）相关命令 ******************************/
	BGREWRITEAOF,
	BGSAVE,
	DBSIZE,
	FLUSHALL,
	FLUSHDB,
	INFO,
	LASTSAVE,
	MONITOR,
	PSYNC,
	SAVE,
	SHUTDOWN,
	SLAVEOF,
	SLOWLOG,
	SYNC,
	TIME;
}