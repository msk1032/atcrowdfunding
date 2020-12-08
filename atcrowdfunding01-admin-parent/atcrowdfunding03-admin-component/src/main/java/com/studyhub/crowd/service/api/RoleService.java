package com.studyhub.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.studyhub.crowd.entity.Role;

/**
 * @author haoren
 * @create 2020-12-07 20:04
 */
public interface RoleService {

    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRoleById(Integer id);
}
