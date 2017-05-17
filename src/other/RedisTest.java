package other;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * java��ʹ��jedis����redis
 * @author Admin
 *
 */
public class RedisTest {
	public static void main(String[] args) throws Exception{
		//����redis������
		Jedis jedis=new Jedis("127.0.0.1",6379);
		//�����ַ���
		jedis.set("key", "value");
		System.out.println(jedis.get("key"));
		jedis.expire("key", 120);
		Thread.sleep(10000);
		System.out.println(jedis.ttl("key"));
		jedis.set("kincr", "12");
		jedis.incr("kincr");
		System.out.println(jedis.get("kincr"));
		
		//������ϣ��
		Map<String,String> map=new HashMap<>();
		map.put("name","dtdyq");
		map.put("age", "19");
		map.put("addr", "sx");
		jedis.hmset("dtd", map);
		System.out.println(jedis.hget("dtd", "name"));
		System.out.println(jedis.hgetAll("dtd"));
		
		//����list:
		jedis.lpush("list", "li1","lide","dweiu");
		jedis.rpush("list","right");
		System.out.println(jedis.lrange("list", 0, -1));
		
		//����set:
		jedis.sadd("set", "dtd","dtd","caoozo","wann","zhannn");
		System.out.println(jedis.smembers("set"));
		jedis.sadd("set2", "two","three");
		System.out.println(jedis.sunion("set","set2"));
	
		//�������򼯺ϣ�
		Map<Double,String> amp=new HashMap<>();
		amp.put(2.0, "uwegi");
		amp.put(3.0, "vehre");
		amp.put(1.0, "ivwen");
		jedis.zadd("zset", amp);
		System.out.println(jedis.zrange("zset", 0, -1));
		
		
	}

}













