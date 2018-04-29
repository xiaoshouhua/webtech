package com.xsh.jedis.client.beta;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.xsh.jedis.client.commands.BasicCommands;
import com.xsh.jedis.client.commands.Command;
import com.xsh.jedis.client.commands.JedisCommands;

public class BinaryClient extends Connection implements BasicCommands,JedisCommands{
	
	private int db;
	
	private boolean isInWatch;
	
	private boolean isInMulti;

	public BinaryClient(String host, int port) throws IOException {
		super(host, port);
	}

	public String ping() {
		return sendCommand(Command.PING);
	}

	public void ping(final byte[] message) {
		sendCommand(Command.PING, message);
	}

	public void set(final byte[] key, final byte[] value) {
		sendCommand(Command.SET, key, value);
	}

	public void get(final byte[] key) {
		sendCommand(Command.GET, key);
	}

	public String quit() {
		db = 0;
		return sendCommand(Command.QUIT);
	}

	public void exists(final byte[]... keys) {
		sendCommand(Command.EXISTS, keys);
	}

	public void del(final byte[]... keys) {
		sendCommand(Command.DEL, keys);
	}

	public void unlink(final byte[]... keys) {
		sendCommand(Command.UNLINK, keys);
	}

	public void type(final byte[] key) {
		sendCommand(Command.TYPE, key);
	}

	public String flushDB() {
		return sendCommand(Command.FLUSHDB);
	}

	public void keys(final byte[] pattern) {
		sendCommand(Command.KEYS, pattern);
	}

	public void randomKey() {
		sendCommand(Command.RANDOMKEY);
	}

	public void rename(final byte[] oldkey, final byte[] newkey) {
		sendCommand(Command.RENAME, oldkey, newkey);
	}

	public void renamenx(final byte[] oldkey, final byte[] newkey) {
		sendCommand(Command.RENAMENX, oldkey, newkey);
	}

	public Long dbSize() {
		return Long.valueOf(sendCommand(Command.DBSIZE));
	}

	public void ttl(final byte[] key) {
		sendCommand(Command.TTL, key);
	}

	public void touch(final byte[]... keys) {
		sendCommand(Command.TOUCH, keys);
	}

	public String flushAll() {
		return sendCommand(Command.FLUSHALL);
	}

	public void getSet(final byte[] key, final byte[] value) {
		sendCommand(Command.GETSET, key, value);
	}

	public void mget(final byte[]... keys) {
		sendCommand(Command.MGET, keys);
	}

	public void setnx(final byte[] key, final byte[] value) {
		sendCommand(Command.SETNX, key, value);
	}

	public void mset(final byte[]... keysvalues) {
		sendCommand(Command.MSET, keysvalues);
	}

	public void msetnx(final byte[]... keysvalues) {
		sendCommand(Command.MSETNX, keysvalues);
	}

	public void decr(final byte[] key) {
		sendCommand(Command.DECR, key);
	}

	public void incr(final byte[] key) {
		sendCommand(Command.INCR, key);
	}

	public void append(final byte[] key, final byte[] value) {
		sendCommand(Command.APPEND, key, value);
	}

	public void hset(final byte[] key, final byte[] field, final byte[] value) {
		sendCommand(Command.HSET, key, field, value);
	}

	public void hset(final byte[] key, final Map<byte[], byte[]> hash) {
		final byte[][] params = new byte[1 + hash.size() * 2][];

		int index = 0;
		params[index++] = key;
		for (final Entry<byte[], byte[]> entry : hash.entrySet()) {
			params[index++] = entry.getKey();
			params[index++] = entry.getValue();
		}
		sendCommand(Command.HSET, params);
	}

	public void hget(final byte[] key, final byte[] field) {
		sendCommand(Command.HGET, key, field);
	}

	public void hsetnx(final byte[] key, final byte[] field, final byte[] value) {
		sendCommand(Command.HSETNX, key, field, value);
	}

	public void hexists(final byte[] key, final byte[] field) {
		sendCommand(Command.HEXISTS, key, field);
	}

	public void hlen(final byte[] key) {
		sendCommand(Command.HLEN, key);
	}

	public void hkeys(final byte[] key) {
		sendCommand(Command.HKEYS, key);
	}

	public void hvals(final byte[] key) {
		sendCommand(Command.HVALS, key);
	}

	public void hgetAll(final byte[] key) {
		sendCommand(Command.HGETALL, key);
	}

	public void llen(final byte[] key) {
		sendCommand(Command.LLEN, key);
	}

	public void lpop(final byte[] key) {
		sendCommand(Command.LPOP, key);
	}

	public void rpop(final byte[] key) {
		sendCommand(Command.RPOP, key);
	}

	public void rpoplpush(final byte[] srckey, final byte[] dstkey) {
		sendCommand(Command.RPOPLPUSH, srckey, dstkey);
	}

	public void smembers(final byte[] key) {
		sendCommand(Command.SMEMBERS, key);
	}

	public void spop(final byte[] key) {
		sendCommand(Command.SPOP, key);
	}

	public void smove(final byte[] srckey, final byte[] dstkey, final byte[] member) {
		sendCommand(Command.SMOVE, srckey, dstkey, member);
	}

	public void scard(final byte[] key) {
		sendCommand(Command.SCARD, key);
	}

	public void sismember(final byte[] key, final byte[] member) {
		sendCommand(Command.SISMEMBER, key, member);
	}

	public void sinter(final byte[]... keys) {
		sendCommand(Command.SINTER, keys);
	}

	public void sunion(final byte[]... keys) {
		sendCommand(Command.SUNION, keys);
	}

	public void sdiff(final byte[]... keys) {
		sendCommand(Command.SDIFF, keys);
	}

	public void srandmember(final byte[] key) {
		sendCommand(Command.SRANDMEMBER, key);
	}

	public void zrank(final byte[] key, final byte[] member) {
		sendCommand(Command.ZRANK, key, member);
	}

	public void zrevrank(final byte[] key, final byte[] member) {
		sendCommand(Command.ZREVRANK, key, member);
	}

	public void zcard(final byte[] key) {
		sendCommand(Command.ZCARD, key);
	}

	public void zscore(final byte[] key, final byte[] member) {
		sendCommand(Command.ZSCORE, key, member);
	}

	public void multi() {
		sendCommand(Command.MULTI);
		isInMulti = true;
	}

	public void discard() {
		sendCommand(Command.DISCARD);
		isInMulti = false;
		isInWatch = false;
	}

	public void exec() {
		sendCommand(Command.EXEC);
		isInMulti = false;
		isInWatch = false;
	}

	public void watch(final byte[]... keys) {
		sendCommand(Command.WATCH, keys);
		isInWatch = true;
	}

	public void unwatch() {
		sendCommand(Command.UNWATCH);
		isInWatch = false;
	}

	public void sort(final byte[] key) {
		sendCommand(Command.SORT, key);
	}

	public void blpop(final byte[][] args) {
		sendCommand(Command.BLPOP, args);
	}

	public void brpop(final byte[][] args) {
		sendCommand(Command.BRPOP, args);
	}

	public String auth(final String password) {
		return sendCommand(Command.AUTH, password.getBytes());
	}

	public void subscribe(final byte[]... channels) {
		sendCommand(Command.SUBSCRIBE, channels);
	}

	public void publish(final byte[] channel, final byte[] message) {
		sendCommand(Command.PUBLISH, channel, message);
	}

	public void unsubscribe() {
		sendCommand(Command.UNSUBSCRIBE);
	}

	public void unsubscribe(final byte[]... channels) {
		sendCommand(Command.UNSUBSCRIBE, channels);
	}

	public void psubscribe(final byte[]... patterns) {
		sendCommand(Command.PSUBSCRIBE, patterns);
	}

	public void punsubscribe() {
		sendCommand(Command.PUNSUBSCRIBE);
	}

	public void punsubscribe(final byte[]... patterns) {
		sendCommand(Command.PUNSUBSCRIBE, patterns);
	}

	public void pubsub(final byte[]... args) {
		sendCommand(Command.PUBSUB, args);
	}

	public String save() {
		return sendCommand(Command.SAVE);
	}

	public String bgsave() {
		return sendCommand(Command.BGSAVE);
	}

	public String bgrewriteaof() {
		return sendCommand(Command.BGREWRITEAOF);
	}

	public Long lastsave() {
		return Long.valueOf(sendCommand(Command.LASTSAVE));
	}

	public String shutdown() {
		return sendCommand(Command.SHUTDOWN);
	}

	public String info() {
		return sendCommand(Command.INFO);
	}

	public String info(final String section) {
		return sendCommand(Command.INFO, section.getBytes());
	}

	public void monitor() {
		sendCommand(Command.MONITOR);
	}

	@Override
	public String set(String key, String value) {
		return sendCommand(Command.SET,key.getBytes(),value.getBytes());
	}

	@Override
	public String get(String key) {
		return sendCommand(Command.SET,key.getBytes());
	}

	@Override
	public Boolean exists(String key) {
		return Boolean.valueOf(sendCommand(Command.SET,key.getBytes()));
	}

	@Override
	public Long persist(String key) {
		return null;
	}

	@Override
	public String type(String key) {
		return null;
	}

	@Override
	public byte[] dump(String key) {
		return null;
	}

	@Override
	public String restore(String key, int ttl, byte[] serializedValue) {
		
		return null;
	}

	@Override
	public Long expire(String key, int seconds) {
		
		return null;
	}

	@Override
	public Long pexpire(String key, long milliseconds) {
		
		return null;
	}

	@Override
	public Long expireAt(String key, long unixTime) {
		
		return null;
	}

	@Override
	public Long pexpireAt(String key, long millisecondsTimestamp) {
		
		return null;
	}

	@Override
	public Long ttl(String key) {
		
		return null;
	}

	@Override
	public Long pttl(String key) {
		
		return null;
	}

	@Override
	public Long touch(String key) {
		
		return null;
	}

	@Override
	public Boolean setbit(String key, long offset, boolean value) {
		
		return null;
	}

	@Override
	public Boolean setbit(String key, long offset, String value) {
		
		return null;
	}

	@Override
	public Boolean getbit(String key, long offset) {
		
		return null;
	}

	@Override
	public Long setrange(String key, long offset, String value) {
		
		return null;
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		
		return null;
	}

	@Override
	public String getSet(String key, String value) {
		
		return null;
	}

	@Override
	public Long setnx(String key, String value) {
		
		return null;
	}

	@Override
	public String setex(String key, int seconds, String value) {
		
		return null;
	}

	@Override
	public String psetex(String key, long milliseconds, String value) {
		
		return null;
	}

	@Override
	public Long decrBy(String key, long decrement) {
		
		return null;
	}

	@Override
	public Long decr(String key) {
		
		return null;
	}

	@Override
	public Long incrBy(String key, long increment) {
		
		return null;
	}

	@Override
	public Double incrByFloat(String key, double increment) {
		
		return null;
	}

	@Override
	public Long incr(String key) {
		
		return null;
	}

	@Override
	public Long append(String key, String value) {
		
		return null;
	}

	@Override
	public String substr(String key, int start, int end) {
		
		return null;
	}

	@Override
	public Long hset(String key, String field, String value) {
		
		return null;
	}

	@Override
	public Long hset(String key, Map<String, String> hash) {
		
		return null;
	}

	@Override
	public String hget(String key, String field) {
		
		return null;
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		
		return null;
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		
		return null;
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		
		return null;
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		
		return null;
	}

	@Override
	public Double hincrByFloat(String key, String field, double value) {
		
		return null;
	}

	@Override
	public Boolean hexists(String key, String field) {
		
		return null;
	}

	@Override
	public Long hdel(String key, String... field) {
		
		return null;
	}

	@Override
	public Long hlen(String key) {
		
		return null;
	}

	@Override
	public Set<String> hkeys(String key) {
		
		return null;
	}

	@Override
	public List<String> hvals(String key) {
		
		return null;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		
		return null;
	}

	@Override
	public Long rpush(String key, String... string) {
		
		return null;
	}

	@Override
	public Long lpush(String key, String... string) {
		
		return null;
	}

	@Override
	public Long llen(String key) {
		
		return null;
	}

	@Override
	public List<String> lrange(String key, long start, long stop) {
		
		return null;
	}

	@Override
	public String ltrim(String key, long start, long stop) {
		
		return null;
	}

	@Override
	public String lindex(String key, long index) {
		
		return null;
	}

	@Override
	public String lset(String key, long index, String value) {
		
		return null;
	}

	@Override
	public Long lrem(String key, long count, String value) {
		
		return null;
	}

	@Override
	public String lpop(String key) {
		
		return null;
	}

	@Override
	public String rpop(String key) {
		
		return null;
	}

	@Override
	public Long sadd(String key, String... member) {
		
		return null;
	}

	@Override
	public Set<String> smembers(String key) {
		
		return null;
	}

	@Override
	public Long srem(String key, String... member) {
		
		return null;
	}

	@Override
	public String spop(String key) {
		
		return null;
	}

	@Override
	public Set<String> spop(String key, long count) {
		
		return null;
	}

	@Override
	public Long scard(String key) {
		
		return null;
	}

	@Override
	public Boolean sismember(String key, String member) {
		
		return null;
	}

	@Override
	public String srandmember(String key) {
		
		return null;
	}

	@Override
	public List<String> srandmember(String key, int count) {
		
		return null;
	}

	@Override
	public Long strlen(String key) {
		
		return null;
	}

	@Override
	public Long zadd(String key, double score, String member) {
		
		return null;
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers) {
		
		return null;
	}

	@Override
	public Set<String> zrange(String key, long start, long stop) {
		
		return null;
	}

	@Override
	public Long zrem(String key, String... members) {
		
		return null;
	}

	@Override
	public Double zincrby(String key, double increment, String member) {
		
		return null;
	}

	@Override
	public Long zrank(String key, String member) {
		
		return null;
	}

	@Override
	public Long zrevrank(String key, String member) {
		
		return null;
	}

	@Override
	public Set<String> zrevrange(String key, long start, long stop) {
		
		return null;
	}

	@Override
	public Long zcard(String key) {
		
		return null;
	}

	@Override
	public Double zscore(String key, String member) {
		
		return null;
	}

	@Override
	public List<String> sort(String key) {
		
		return null;
	}

	@Override
	public Long zcount(String key, double min, double max) {
		
		return null;
	}

	@Override
	public Long zcount(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		
		return null;
	}

	@Override
	public Long zremrangeByRank(String key, long start, long stop) {
		
		return null;
	}

	@Override
	public Long zremrangeByScore(String key, double min, double max) {
		
		return null;
	}

	@Override
	public Long zremrangeByScore(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Long zlexcount(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
		
		return null;
	}

	@Override
	public Long zremrangeByLex(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Long lpushx(String key, String... string) {
		
		return null;
	}

	@Override
	public Long rpushx(String key, String... string) {
		
		return null;
	}

	@Override
	public List<String> blpop(int timeout, String key) {
		
		return null;
	}

	@Override
	public List<String> brpop(int timeout, String key) {
		
		return null;
	}

	@Override
	public Long del(String key) {
		del(key.getBytes());
		return 1l;
	}

	@Override
	public Long unlink(String key) {
		
		return null;
	}

	@Override
	public String echo(String string) {
		
		return null;
	}

	@Override
	public Long move(String key, int dbIndex) {
		
		return null;
	}

	@Override
	public Long bitcount(String key) {
		
		return null;
	}

	@Override
	public Long bitcount(String key, long start, long end) {
		
		return null;
	}

	@Override
	public Long bitpos(String key, boolean value) {
		
		return null;
	}

	@Override
	public Long pfadd(String key, String... elements) {
		
		return null;
	}

	@Override
	public long pfcount(String key) {
		
		return 0;
	}

	@Override
	public Long geoadd(String key, double longitude, double latitude, String member) {
		
		return null;
	}

	@Override
	public Double geodist(String key, String member1, String member2) {
		
		return null;
	}

	@Override
	public List<String> geohash(String key, String... members) {
		
		return null;
	}

	@Override
	public List<Long> bitfield(String key, String... arguments) {
		
		return null;
	}

	@Override
	public Long hstrlen(String key, String field) {
		
		return null;
	}

	

	@Override
	public String select(int index) {
		
		return null;
	}

	@Override
	public String swapDB(int index1, int index2) {
		
		return null;
	}

	

	@Override
	public String slaveof(String host, int port) {
		
		return null;
	}

	@Override
	public String slaveofNoOne() {
		
		return null;
	}

	@Override
	public int getDB() {
		
		return 0;
	}

	@Override
	public String configResetStat() {
		
		return null;
	}

	@Override
	public String configRewrite() {
		
		return null;
	}

	@Override
	public Long waitReplicas(int replicas, long timeout) {
		
		return null;
	}
}
