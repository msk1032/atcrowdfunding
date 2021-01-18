package com.studyhub.crowd.controller;

import com.studyhub.crowd.constant.CrowdConstant;
import com.studyhub.crowd.entity.po.MemberPO;
import com.studyhub.crowd.service.api.MemberService;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
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


    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveNumber(@RequestBody MemberPO memberPO) {

        try {
            memberService.saveMember(memberPO);

            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {

                return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/memberPO/by/loginAcct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct) {
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
