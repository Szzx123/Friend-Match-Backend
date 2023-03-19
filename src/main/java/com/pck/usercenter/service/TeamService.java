package com.pck.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pck.usercenter.model.domain.User;
import com.pck.usercenter.model.request.TeamUpdateRequest;
import com.pck.usercenter.model.domain.Team;
import com.pck.usercenter.model.dto.TeamQuery;
import com.pck.usercenter.model.request.TeamJoinRequest;
import com.pck.usercenter.model.request.TeamQuitRequest;
import com.pck.usercenter.model.vo.TeamUserVO;

import java.util.List;

/**
* @author shizhuzexuan
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2023-02-18 14:59:44
*/
public interface TeamService extends IService<Team> {
    /**
     * 创建队伍
     * @param team
     * @param loginUser
     * @return
     */
    long addTeam(Team team, User loginUser);

    /**
     * 搜索队伍
     * @param teamQuery
     * @param isAdmin
     * @return
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    /**
     * 更新队伍
     * @param teamUpdateRequest
     * @param loginUser
     * @return
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User loginUser);


    /**
     * 加入队伍请求
     * @param teamJoinRequest
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);

    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    boolean deleteTeam(long id, User loginUser);
}
