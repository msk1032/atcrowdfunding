package com.studyhub.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.studyhub.crowd.entity.Admin;

import java.util.List;

/**
 * @author haoren
 * @create 2020-11-30 13:59
 */
public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPwsd);

    PageInfo<Admin> getAdminPage(String keyword, Integer pageNum, Integer pageSize);
}
