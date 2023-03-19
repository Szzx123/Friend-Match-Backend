package com.pck.usercenter.model.request;


import lombok.Data;

/**
 * 用户退出队伍
 */

@Data
public class TeamQuitRequest {

    private static  final long serialVersionUID = -654765186088840209L;

    private long teamId;
}
