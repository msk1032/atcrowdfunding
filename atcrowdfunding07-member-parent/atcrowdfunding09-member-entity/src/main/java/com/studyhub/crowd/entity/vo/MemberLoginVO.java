package com.studyhub.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author haoren
 * @create 2021-01-18 15:37
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String email;

}
