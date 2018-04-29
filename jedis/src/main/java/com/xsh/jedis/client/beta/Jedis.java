package com.xsh.jedis.client.beta;

import java.io.IOException;
import java.util.Map;

import com.xsh.jedis.client.commands.BasicCommands;
import com.xsh.jedis.client.commands.JedisCommands;

public class Jedis extends BinaryClient implements BasicCommands,JedisCommands{

	public Jedis(String host, int port) throws IOException {
		super(host, port);
	}

	@Override
	public void set(byte[] key, byte[] value) {
		super.set(key, value);
	}

	@Override
	public void del(byte[]... keys) {
		super.del(keys);
	}

	@Override
	public void keys(byte[] pattern) {
		super.keys(pattern);
	}

	@Override
	public Long dbSize() {
		return super.dbSize();
	}

	@Override
	public void mset(byte[]... keysvalues) {
		super.mset(keysvalues);
	}

	@Override
	public String auth(String password) {
		return super.auth(password);
	}

	@Override
	public String set(String key, String value) {
		return super.set(key, value);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return super.hset(key, field, value);
	}

	@Override
	public Long hset(String key, Map<String, String> hash) {
		return super.hset(key, hash);
	}

	@Override
	public Long del(String key) {
		return super.del(key);
	}

	@Override
	public Long move(String key, int dbIndex) {
		return super.move(key, dbIndex);
	}

	
}
