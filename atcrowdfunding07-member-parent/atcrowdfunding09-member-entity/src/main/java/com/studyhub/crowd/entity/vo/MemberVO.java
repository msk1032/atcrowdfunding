package com.studyhub.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author haoren
 * @create 2021-01-18 11:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO implements Serializable {

    private static final long serialVersionUID = 2L;

    private String loginAcct;

    private String userPswd;

    private String username;

    private String email;

    private String code;
}
