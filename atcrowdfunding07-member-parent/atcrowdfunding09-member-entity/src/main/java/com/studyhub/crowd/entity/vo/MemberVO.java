package com.studyhub.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author haoren
 * @create 2021-01-18 11:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String loginAcct;

    private String userPswd;

    private String username;

    private String email;

    private String code;
}
