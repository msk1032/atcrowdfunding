package com.studyhub.crowd.service.impl;

import com.studyhub.crowd.entity.po.MemberPO;
import com.studyhub.crowd.entity.po.MemberPOExample;
import com.studyhub.crowd.mapper.MemberPOMapper;
import com.studyhub.crowd.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author haoren
 * @create 2020-12-18 19:09
 */

@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginAcct) {

        MemberPOExample example = new MemberPOExample();

        MemberPOExample.Criteria criteria = example.createCriteria();

        criteria.andLoginAcctEqualTo(loginAcct);

        List<MemberPO> memberPOList = memberPOMapper.selectByExample(example);

        return memberPOList.get(0);
    }
}
