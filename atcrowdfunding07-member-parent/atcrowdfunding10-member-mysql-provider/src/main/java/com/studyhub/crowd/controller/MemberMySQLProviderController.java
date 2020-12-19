package com.studyhub.crowd.controller;

import com.studyhub.crowd.entity.po.MemberPO;
import com.studyhub.crowd.service.api.MemberService;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haoren
 * @create 2020-12-18 19:01
 */
@RestController
public class MemberMySQLProviderController {

    @Autowired
    private MemberService memberService;


    @RequestMapping("/get/memberPO/by/loginAcct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct){
        //对查询进行异常处理
        try {
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginAcct);

            return ResultEntity.successWithData(memberPO);

        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }
    }
}
