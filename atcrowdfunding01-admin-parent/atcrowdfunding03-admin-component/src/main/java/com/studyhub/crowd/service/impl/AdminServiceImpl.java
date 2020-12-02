package com.studyhub.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.studyhub.crowd.constant.CrowdConstant;
import com.studyhub.crowd.entity.Admin;
import com.studyhub.crowd.entity.AdminExample;
import com.studyhub.crowd.exception.LoginFailedException;
import com.studyhub.crowd.mapper.AdminMapper;
import com.studyhub.crowd.service.api.AdminService;
import com.studyhub.crowd.utils.CrowdUtils;
import org.apache.taglibs.standard.lang.jstl.NullLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author haoren
 * @create 2020-11-30 13:59
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
        throw new RuntimeException();
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPwsd) {
        //1.根据账号查询对象是否存在
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();

        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> list = adminMapper.selectByExample(adminExample);

        //2.判断Admin是否为空 如果为空 抛出异常 不为空检查密码
        if (list == null || list.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        if (list.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Admin admin = list.get(0);

        //3.如果不为空 比较两个加密的密码 相同登录成功 不同抛出异常

        String userPswdDB = admin.getUserPswd();
        String userPswdForm = CrowdUtils.md5(userPwsd);
        if (!Objects.equals(userPswdDB, userPswdForm)) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        return admin;
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public PageInfo<Admin> getAdminPage(String keyword, Integer pageNum, Integer pageSize) {
        //1.开启分页功能
        PageHelper.startPage(pageNum, pageSize);

        //2.查询Admin数据
        List<Admin> adminList = adminMapper.selectAdminListByKeyword(keyword);

        //3.将数据封装为PageInfo之后返回
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(adminList);
        return pageInfo;
    }
}
