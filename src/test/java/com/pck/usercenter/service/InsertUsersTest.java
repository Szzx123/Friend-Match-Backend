package com.pck.usercenter.service;

import com.pck.usercenter.model.domain.User;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
public class InsertUsersTest {


    //    private UserMapper userMapper;
    @Resource
    private UserService userService;

    private ExecutorService executorService = new ThreadPoolExecutor(40, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));

    @Test
    public void doInsertUser() {
        StopWatch stopWatch = new StopWatch();
        final int INSERT_NUM = 10000;
        stopWatch.start();
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("ffffuuuu");
            user.setUserAccount("fakeuser");
            user.setAvatarUrl("https://img2.baidu.com/it/u=1790834130,1952230725&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
            user.setGender(0);
            user.setUserPassword("1234567890");
            user.setPhone("0655472890");
            user.setEmail("testuer@gmail.com");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlanetCode("1111");
            user.setTags("[]");
            userList.add(user);
        }
        userService.saveBatch(userList, 1000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    @Test
    public void doCuncurrencyInsertUser() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 10000;
        int batchSize = 5000;
        int j = 0;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            List<User> userList = new ArrayList<>();
            while (true) {
                j++;
                User user = new User();
                user.setUsername("ffffuuuu");
                user.setUserAccount("fakeuser");
                user.setAvatarUrl("https://img2.baidu.com/it/u=1790834130,1952230725&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
                user.setGender(0);
                user.setUserPassword("1234567890");
                user.setPhone("0655472890");
                user.setEmail("testuer@gmail.com");
                user.setUserStatus(0);
                user.setUserRole(0);
                user.setPlanetCode("1111");
                user.setTags("[]");
                userList.add(user);
                if (j % batchSize == 0) {
                    break;
                }
            }
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                userService.saveBatch(userList, batchSize);
            }, executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
