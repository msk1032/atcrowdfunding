package com.studyhub.crowd.api.member;

import com.studyhub.crowd.entity.po.MemberPO;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author haoren
 * @create 2020-12-18 18:52
 */

@FeignClient("studyhub-crowd-mysql")
public interface MemberMySQLRemoteService {

    @RequestMapping("/get/memberPO/by/loginAcct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct);

    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveNumber(@RequestBody MemberPO memberPO);
}
