package com.pck.usercenter.common;


import lombok.Data;

import java.io.Serializable;

@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = -5689221020507614166L;

    protected int pageSize = 10;

    protected int pageNum = 1;

}
