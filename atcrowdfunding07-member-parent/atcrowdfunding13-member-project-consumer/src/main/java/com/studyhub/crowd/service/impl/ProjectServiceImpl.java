package com.studyhub.crowd.service.impl;

import com.studyhub.crowd.entity.vo.MemberConfirmInfoVO;
import com.studyhub.crowd.entity.vo.MemberLauchInfoVO;
import com.studyhub.crowd.entity.vo.ReturnVO;
import com.studyhub.crowd.service.api.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author haoren
 * @create 2021-01-19 13:48
 */
@Service
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

    private static final long serialVersionUID = 1L;

    private List<Integer> typeIdList;
    // 标签 id 集合
    private List<Integer> tagIdList;
    // 项目名称
    private String projectName;
    // 项目描述
    private String projectDescription;
    // 计划筹集的金额
    private Integer money;
    // 筹集资金的天数
    private Integer day;
    // 创建项目的日期
    private String createdate;
    // 头图的路径
    private String headerPicturePath;
    // 详情图片的路径
    private List<String> detailPicturePathList;
    // 发起人信息
    private MemberLauchInfoVO memberLauchInfoVO;
    // 回报信息集合
    private List<ReturnVO> returnVOList;
    // 发起人确认信息
    private MemberConfirmInfoVO memberConfirmInfoVO;
}
