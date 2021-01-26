package com.studyhub.crowd.controller;


import com.studyhub.crowd.api.member.MemberMySQLRemoteService;
import com.studyhub.crowd.api.member.MemberRedisRemoteService;
import com.studyhub.crowd.config.OSSProperties;
import com.studyhub.crowd.constant.CrowdConstant;
import com.studyhub.crowd.entity.vo.ProjectVo;
import com.studyhub.crowd.service.api.ProjectService;
import com.studyhub.crowd.utils.CrowdUtils;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author haoren
 * @create 2021-01-19 13:47
 */
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private OSSProperties ossProperties;
    @Autowired
    private MemberMySQLRemoteService mySQLRemoteService;
    @Autowired
    private MemberRedisRemoteService redisRemoteService;

    @RequestMapping("/create/project/information")
    public String saveProjectBasicInfo(ProjectVo projectVo,
                                       MultipartFile headPicture,
                                       List<MultipartFile> detailPictureList,
                                       HttpSession session,
                                       ModelMap modelMap) throws IOException {
        //1.完成头图上传
        if (!headPicture.isEmpty()) {
            ResultEntity<String> uploadHeadPictureResult = CrowdUtils.uploadFileToOSS(ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecret(),
                    headPicture.getInputStream(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    headPicture.getOriginalFilename()
            );

            if(ResultEntity.SUCCESS.equals(uploadHeadPictureResult.getResult())) {
                projectVo.setHeaderPicturePath(uploadHeadPictureResult.getData());
            }else{
                modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_UPLOAD_HEAD_PICTURE_FAILED);
                return "project-launch";
            }
        }

        //2.完成详细图片上传


        //3.
        return null;


    }
}
