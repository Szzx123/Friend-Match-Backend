package com.pck.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author shizhuzexuan
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;
}
