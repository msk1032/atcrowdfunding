package com.studyhub.crowd.controller;

import com.studyhub.crowd.api.member.MemberMySQLRemoteService;
import com.studyhub.crowd.api.member.MemberRedisRemoteService;
import com.studyhub.crowd.config.EmailConfigProperties;
import com.studyhub.crowd.constant.CrowdConstant;
import com.studyhub.crowd.entity.po.MemberPO;
import com.studyhub.crowd.entity.vo.MemberVO;
import com.studyhub.crowd.utils.CrowdUtils;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author haoren
 * @create 2021-01-17 18:31
 */
@Controller
public class MemberController {

    @Autowired
    private MemberRedisRemoteService memberRedisRemoteService;
    @Autowired
    private MemberMySQLRemoteService memberMySQLRemoteService;
    @Autowired
    private EmailConfigProperties properties;

    @RequestMapping("/auth/do/member/register")
    public String register(MemberVO memberVO, ModelMap modelMap) {
        //1.获取保存到redis中的验证码
        String key = CrowdConstant.REDIS_CODE_PREFIX + memberVO.getEmail();
        ResultEntity<String> redisCodeResult = memberRedisRemoteService.getRedisStringValueByKeyRemote(key);


        //2.判断是否获取成功
        if (ResultEntity.FAILED.equals(redisCodeResult.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, redisCodeResult.getMessage());

            return "member-reg";
        }
        if (ResultEntity.SUCCESS.equals(redisCodeResult.getResult())) {
            //判断验证码是否相等
            if (Objects.equals(memberVO.getCode(), redisCodeResult.getData())) {
                //相等 删除redis中的验证码
                memberRedisRemoteService.removeRedisKeyRemote(key);
                //密码加密
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodePassword = passwordEncoder.encode(memberVO.getUserPswd());
                memberVO.setUserPswd(encodePassword);

                //保存用户数据
                MemberPO memberPO = new MemberPO();
                BeanUtils.copyProperties(memberVO, memberPO);
                ResultEntity<String> saveMemberResult = memberMySQLRemoteService.saveNumber(memberPO);

                if(ResultEntity.FAILED.equals(saveMemberResult.getResult())) {

                    modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, saveMemberResult.getMessage());

                    return "member-reg";
                }
            }else{
                modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_NOT_EXISTS);

                return "member-reg";

            }
        }

        return "redirect:/auth/member/to/login/page";
    }

    @ResponseBody
    @RequestMapping("/auth/member/send/email.json")
    public ResultEntity<String> sendCode(@RequestParam("email") String email) {

        String code = CrowdUtils.messageCode();

        String info = "【尚筹网】尊敬的用户你好，你注册的验证码：" + code + "，如非本人操作，请忽略。";

        ResultEntity<String> sendEmailResult = CrowdUtils.SendEmail(properties.getSendEmailAccount(),
                properties.getSendEmailPassword(),
                properties.getSendEmailSMTPServer(),
                properties.getSmtpPort(),
                email,
                info);

        // 发送成功 存入redis
        if (ResultEntity.SUCCESS.equals(sendEmailResult.getResult())) {

            String key = CrowdConstant.REDIS_CODE_PREFIX + email;
            ResultEntity<String> saveCodeResult = memberRedisRemoteService.setRedisKeyValueRemoteWithTimeout(key, code, 15, TimeUnit.MINUTES);

            if (ResultEntity.SUCCESS.equals(saveCodeResult.getResult())) {

                return ResultEntity.successWithoutData();
            }
            return saveCodeResult;
        }

        return sendEmailResult;
    }

}
