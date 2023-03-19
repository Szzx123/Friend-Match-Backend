//package com.pck.usercenter.once;
//import java.util.Date;
//
//import com.pck.usercenter.mapper.UserMapper;
//import com.pck.usercenter.model.domain.User;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//import javax.annotation.Resource;
//
////写成bean
//@Component
//public class InsertUsers {
//    @Resource
//    private UserMapper userMapper;
//    @Scheduled(initialDelay = 5000, fixedRate = Long.MAX_VALUE)
//    public void doInsertUser() {
//        StopWatch stopWatch = new StopWatch();
//        final int INSERT_NUM = 1000;
//        stopWatch.start();
//        for (int i = 0; i < INSERT_NUM; i++) {
//            User user = new User();
//            user.setUsername("ffffuuuu");
//            user.setUserAccount("fakeuser");
//            user.setAvatarUrl("https://img2.baidu.com/it/u=1790834130,1952230725&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
//            user.setGender(0);
//            user.setUserPassword("1234567890");
//            user.setPhone("0655472890");
//            user.setEmail("testuer@gmail.com");
//            user.setUserStatus(0);
//            user.setUserRole(0);
//            user.setPlanetCode("1111");
//            user.setTags("[]");
//            userMapper.insert(user);
//        }
//        stopWatch.stop();
//        System.out.println(stopWatch.getTotalTimeMillis());
//    }
//}
