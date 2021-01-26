package com.studyhub.crowd.entity.vo;

import java.io.Serializable;

/**
 * @author haoren
 * @create 2021-01-19 13:56
 */
public class MemberLauchInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 简单介绍
    private String descriptionSimple;
    // 详细介绍
    private String descriptionDetail;
    // 联系电话
    private String phoneNum;
    // 客服电话
    private String serviceNum;
}
