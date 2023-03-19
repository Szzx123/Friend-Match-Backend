package com.pck.usercenter.job;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pck.usercenter.model.domain.User;
import com.pck.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存预热
 */
@Component
@Slf4j
public class PreCacheJob {
    @Resource
    private UserService userService;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private List<Long> mainUserList = Arrays.asList(1L);

    @Scheduled(cron = "0 59 23 * * *")
    public void doCacheRecommendUser() {
        RLock lock = redissonClient.getLock("yupao:precachejob:docache:lock");
        try {
            //只有一个线程能获取锁
            if (lock.tryLock(0, -1, TimeUnit.MILLISECONDS)){
                System.out.println("get lock" + Thread.currentThread().getId());
                for (Long userId : mainUserList) {
                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                    Page<User> userPage = userService.page(new Page<>(1, 20), queryWrapper);
                    String redisKey = String.format("yupao:user:recommand:%s", userId);
                    ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
                    try {
                        valueOperations.set(redisKey, userPage, 3000000, TimeUnit.MILLISECONDS);
                    } catch (Exception e) {
                        log.error("redis set key error", e);
    //            throw new RuntimeException(e);
                    }
                }
                //只释放自己的锁
            }
        } catch (InterruptedException e) {
            log.error("doCacheRecommendUser error", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println("unlock" + Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }
}
