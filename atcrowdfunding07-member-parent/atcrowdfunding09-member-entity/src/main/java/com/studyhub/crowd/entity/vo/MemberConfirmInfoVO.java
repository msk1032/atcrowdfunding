package com.studyhub.crowd.entity.vo;

import java.io.Serializable;

/**
 * @author haoren
 * @create 2021-01-19 13:57
 */
public class MemberConfirmInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 易付宝账号
    private String paynum;
    // 法人身份证号
    private String cardnum;
}
