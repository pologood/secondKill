package com.maoshen.secondkill.service.base;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.maoshen.common.constant.CommonKey;
import com.maoshen.component.other.TimeUtil;
import com.maoshen.component.redis.RedisService;

public abstract class SecondKillBaseService {
	@Autowired
	private RedisService redisService;

	/**
	 * 从缓存获取今天抽奖次数
	 * 
	 * @param userId
	 * @param eventId
	 * @return
	 * @throws Exception
	 */
	public long getLotteryCount(long userId, long eventId, Integer day) throws Exception {
		String key = String.format(CommonKey.COMMON_SECONDKILL_COUNT, userId, eventId, day);
		Object resultObj = redisService.getByValue(key);
		if (resultObj == null) {
			return 0;
		}
		Long count = Long.parseLong(resultObj.toString());
		return count;
	}

	public void lotteryIncrEventDay(long userId, long eventId, Integer day) {
		String key = String.format(CommonKey.COMMON_SECONDKILL_COUNT, userId, eventId, day);
		redisService.incr(key,TimeUtil.getTodayEndLastTime(),TimeUnit.MILLISECONDS);
	}
}
