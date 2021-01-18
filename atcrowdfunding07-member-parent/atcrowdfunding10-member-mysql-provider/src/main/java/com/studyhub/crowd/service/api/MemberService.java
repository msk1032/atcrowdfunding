package com.studyhub.crowd.service.api;

import com.studyhub.crowd.entity.po.MemberPO;

/**
 * @author haoren
 * @create 2020-12-18 19:09
 */
public interface MemberService {
    MemberPO getMemberPOByLoginAcct(String loginAcct);

    void saveMember(MemberPO memberPO);
}
