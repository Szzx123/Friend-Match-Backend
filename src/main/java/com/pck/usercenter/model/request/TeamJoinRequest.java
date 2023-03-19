package com.pck.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登陆请求体
 */
@Data
public class TeamJoinRequest implements Serializable {
    private static final long serialVersionUID = -2155274441444614083L;

    /**
     * id
     */
    private long teamId;

    /**
     * password
     */
    private String password;
}
