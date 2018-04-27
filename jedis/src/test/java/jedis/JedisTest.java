package jedis;

import java.io.IOException;
import com.xsh.jedis.client.beta.Jedis;

public class JedisTest {

	public static void main(String[] args) throws IOException {

		Jedis jRedis = new Jedis("172.16.21.205", 6379);
		System.out.println(jRedis.auth("fcpwd1305"));
		System.out.println(jRedis.set("key_name1", "xxxxxxx"));
		System.out.println(jRedis.get("key_name1"));
		System.out.println(jRedis.del("key_name1"));
		

	}

}
