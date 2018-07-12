package com.loeo.base.cache;

import com.loeo.base.IdEntity;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Tony.L
 * @version ：2018 Version：1.0
 * @create ：2018/07/11 10:45
 */
@Component("redisUtils")
public class RedisUtils {

	@Resource(name = "redisTemplate")
	protected RedisTemplate<String, Object> redisTemplate;
	@Resource(name = "hashOperations")
	protected HashOperations<String, String, Object> hashOps;
	@Resource(name = "setOperations")
	protected SetOperations<String, Object> setOps;
	@Resource(name = "zSetOperations")
	protected ZSetOperations<String, Object> zSetOps;
	@Resource(name = "valueOperations")
	protected ValueOperations<String, Object> vOps;

	public RedisUtils() {
	}

	public Boolean isExist(String cacheKey) {
		return redisTemplate.hasKey(cacheKey);
	}

	public Set<String> getKeys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * 设置过期时间：单位天
	 *
	 * @param key
	 * @param expire
	 */
	public void setExpire(String key, long expire) {
		setExpire(key, expire, null);
	}

	/**
	 * 支持自己传单位
	 *
	 * @param key
	 * @param expire
	 * @param timeUnit
	 */
	public void setExpire(String key, long expire, TimeUnit timeUnit) {
		if (expire != -1) {
			if (timeUnit == null) {
				redisTemplate.expire(key, expire, TimeUnit.DAYS);
			} else {
				redisTemplate.expire(key, expire, timeUnit);
			}
		}
	}

	/**
	 * Hash操作
	 */

	public void hPut(String key, String hashKey, Object value) {
		hPut(key, hashKey, value, -1);
	}

	/**
	 * 添加
	 *
	 * @param key     缓存key
	 * @param hashKey hash中的key
	 * @param value   hash中的value
	 * @param expire  过期时间(单位:天),传入 -1 时表示不设置过期时间
	 */
	public void hPut(String key, String hashKey, Object value, long expire) {
		hPut(key, hashKey, value, expire, null);
	}

	public void hPut(String key, String hashKey, Object value, long expire, TimeUnit timeUnit) {
		hashOps.put(key, hashKey, value);
		setExpire(key, expire, timeUnit);
	}

	/**
	 * 使用idEntity 的id作为hashKey
	 *
	 * @param key
	 * @param idEntity
	 * @param expire
	 */
	public <T extends IdEntity> void hPut(String key, T idEntity, long expire) {
		hPut(key, idEntity.getId(), idEntity, expire);
	}

	public <T extends IdEntity> void hPut(String key, T idEntity) {
		hPut(key, idEntity, -1);
	}

	public void hPut(String key, Iterable<? extends IdEntity> idEntities, long expire) {
		if (idEntities == null) {
			return;
		}
		for (IdEntity idEntity : idEntities) {
			hPut(key, idEntity, expire);
		}
	}

	public void hPut(String key, Iterable<? extends IdEntity> idEntities) {
		hPut(key, idEntities, -1);
	}


	/**
	 * 获取一个对象
	 *
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object hGet(String key, String hashKey) {
		return hashOps.get(key, hashKey);
	}

	/**
	 * 获取多个key
	 *
	 * @param key
	 * @param hashKeys
	 * @return
	 */
	public List hGet(String key, Collection<String> hashKeys) {
		if (CollectionUtils.isEmpty(hashKeys)) {
			return Collections.emptyList();
		}
		List list = hashOps.multiGet(key, hashKeys);
		//dangdong 处理spring data redis查询不到返回空值的问题
		List resultList = new ArrayList();
		if (list.size() > 0) {
			list.stream().filter(obj -> obj != null).forEach(resultList::add);
		}
		return resultList;
	}

	/**
	 * 判断hash中是否存在hashKey
	 *
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Boolean hExists(String key, String hashKey) {
		return hashOps.hasKey(key, hashKey);
	}

	public Long hDel(String key, String... hashKeys) {
		if (hashKeys == null || hashKeys.length == 0) {
			return -1L;
		}
		return hashOps.delete(key, hashKeys);
	}

	/**
	 * 得到Hash中的所有key
	 *
	 * @param key
	 * @return
	 */
	public Set<String> hKeys(String key) {
		return hashOps.keys(key);
	}

	/**
	 * 得到key中的所有元素
	 *
	 * @param key
	 * @return
	 */
	public List hGetAll(String key) {
		return hashOps.values(key);
	}

	/**
	 * 得到Hash存放元素的数量
	 *
	 * @param key
	 * @return
	 */
	public Long hLen(String key) {
		return hashOps.size(key);
	}

	/**
	 * Set操作
	 */

	public void sAdd(String key, Object[] values) {
		sAdd(key, values, -1);
	}

	/**
	 * 向集合添加一个或多个成员
	 *
	 * @param key
	 * @param values
	 * @param expire
	 */
	public void sAdd(String key, Object[] values, long expire) {
		sAdd(key, values, expire, null);
	}

	public void sAdd(String key, Object[] values, long expire, TimeUnit timeUnit) {
		if (values == null || values.length == 0) {
			return;
		}
		setOps.add(key, values);
		setExpire(key, expire, timeUnit);
	}

	/**
	 * 获取集合的成员数
	 *
	 * @param key
	 * @return
	 */
	public Long sCard(String key) {
		return setOps.size(key);
	}

	/**
	 * 返回集合中的所有成员
	 *
	 * @param key
	 * @return
	 */
	public Set sMembers(String key) {
		return setOps.members(key);
	}

	/**
	 * 移除集合中一个或多个成员
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public Long sRem(String key, Object... values) {
		if (values == null || values.length == 0) {
			return -1L;
		}
		return setOps.remove(key, values);
	}

	/**
	 * 判断 member 元素是否是集合 key 的成员
	 *
	 * @param key
	 * @param object
	 * @return
	 */
	public Boolean sIsMember(String key, Object object) {
		return setOps.isMember(key, object);
	}

	/**
	 * 操作Stored set
	 */

	public Boolean zAdd(String key, Object value, double store) {
		return zAdd(key, value, store, -1);
	}

	/**
	 * 向有序集合添加一个或者更新已存在成员的分数
	 *
	 * @param key   键
	 * @param value 值
	 * @param store 分数
	 * @return 是否添加成功
	 */
	public Boolean zAdd(String key, Object value, double store, long expire) {
		return zAdd(key, value, store, expire, null);
	}

	public Boolean zAdd(String key, Object value, double store, long expire, TimeUnit timeUnit) {
		Boolean result = zSetOps.add(key, value, store);
		setExpire(key, expire, timeUnit);
		return result;
	}

	/**
	 * 向有序集合添加多个成员，或者更新已存在成员的分数
	 *
	 * @param key
	 * @param tuples
	 * @return
	 */
	public Long zAdd(String key, Set<TypedTuple<Object>> tuples) {
		if (CollectionUtils.isEmpty(tuples)) {
			return -1L;
		}
		return zSetOps.add(key, tuples);
	}

	/**
	 * 获取有序集合的成员数
	 *
	 * @param key
	 * @return
	 */
	public Long zCard(String key) {
		return zSetOps.zCard(key);
	}

	/**
	 * 计算在有序集合中指定区间分数的成员数
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zCount(String key, double min, double max) {
		return zSetOps.count(key, min, max);
	}

	/**
	 * 有序集合中对指定成员的分数加上增量 increment
	 *
	 * @param key
	 * @param value 要增加分数的成员
	 * @param delta 增量
	 * @return
	 */
	public Double zIncrBy(String key, Object value, double delta) {
		return zSetOps.incrementScore(key, value, delta);
	}

	/**
	 * 通过索引区间返回有序集合成指定区间内的成员
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set zRange(String key, long start, long end) {
		return zSetOps.range(key, start, end);
	}

	/**
	 * 通过字典区间返回有序集合的成员
	 *
	 * @param key
	 * @param range
	 * @return
	 */
	public Set zRangeByLex(String key, Range range) {
		return zSetOps.rangeByLex(key, range);
	}

	/**
	 * 通过分数返回有序集合指定区间内的成员
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set zRangeByScore(String key, double min, double max) {
		return zSetOps.rangeByScore(key, min, max);
	}

	/**
	 * 返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列。
	 * 默认情况下，区间的取值使用闭区间 (小于等于或大于等于)
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param offset 从哪一条开始（不包含这一条）
	 * @param count  取多少条
	 * @return
	 */
	public Set zRangeByScore(String key, double min, double max, long offset, long count) {
		return zSetOps.rangeByScore(key, min, max, offset, count);
	}

	/**
	 * 返回有序集合中指定成员的索引
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Long zRank(String key, Object value) {
		return zSetOps.rank(key, value);
	}

	/**
	 * 移除有序集合中的一个或多个成员
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public Long zRem(String key, Object[] values) {
		if (values == null || values.length == 0) {
			return -1L;
		}
		return zSetOps.remove(key, values);
	}


	/**
	 * 移除有序集合中给定的分数区间的所有成员
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zRemRangeByScore(String key, double min, double max) {
		return zSetOps.removeRangeByScore(key, min, max);
	}

	/**
	 * 返回有序集中指定分数区间内的成员，分数从高到低排序
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set zRevRangeByScore(String key, double min, double max) {
		return zSetOps.reverseRangeByScore(key, min, max);
	}

	/**
	 * 返回有序集中指定分数区间内的从offset开始的count个成员，分数从高到低排序
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param offset 从哪一条开始（不包含这一条）
	 * @param count  去多少条
	 * @return
	 */
	public Set zRevRangeByScore(String key, double min, double max, long offset, long count) {
		return zSetOps.reverseRangeByScore(key, min, max, offset, count);
	}

	/**
	 * String 操作
	 */

	/**
	 * 设置指定 key 的值
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		set(key, value, -1);
	}

	/**
	 * 获取指定 key 的值带过期时间
	 *
	 * @param key
	 * @param value
	 * @param expire
	 */
	public void set(String key, Object value, long expire) {
		set(key, value, expire, null);
	}

	public void set(String key, Object value, long expire, TimeUnit timeUnit) {
		vOps.set(key, value);
		setExpire(key, expire, timeUnit);
	}
	/**
	 * 获取指定 key 的值。
	 *
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		Object object = vOps.get(key);
		if(object==null){
			return "";
		}else {
			return String.valueOf(object);
		}
	}

	/**
	 * 获取指定 key 的值。
	 *
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		Object object = vOps.get(key);
		if(object==null){
			return 0;
		}else {
			return Integer.parseInt(String.valueOf(object));
		}
	}
	/**
	 * 获取指定 key 的值。
	 *
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return vOps.get(key);
	}

	/**
	 * 只有在 key 不存在时设置 key 的值。
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean setNX(String key, String value) {
		return vOps.setIfAbsent(key, value);
	}

	/**
	 * 只有在 key 不存在时设置 key 的值。带过期时间
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean setNX(String key, String value, long expire) {
		Boolean result = vOps.setIfAbsent(key, value);
		if (result) {
			setExpire(key, expire);
		}
		return result;
	}

	/**
	 * 将 key 中储存的数字值增一。
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作
	 * @param key
	 */
	public Long increment(String key){
		return vOps.increment(key,1);
	}
	/**
	 * 管道操作
	 *
	 * @param redisCallback
	 * @return
	 */
	public List<Object> pipelined(RedisCallback<?> redisCallback) {
		return redisTemplate.executePipelined(redisCallback);
	}


	public void del(String key) {
		redisTemplate.delete(key);
	}

	public void del(Collection<String> keys) {
		List<String> keyList = new ArrayList<>();
		keyList.addAll(keys);
		redisTemplate.delete(keyList);
	}

}
