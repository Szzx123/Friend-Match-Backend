package com.pck.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pck.usercenter.mapper.UserTeamMapper;
import com.pck.usercenter.service.UserTeamService;
import com.pck.usercenter.model.domain.UserTeam;
import org.springframework.stereotype.Service;

/**
* @author shizhuzexuan
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2023-02-18 15:01:16
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {

}




