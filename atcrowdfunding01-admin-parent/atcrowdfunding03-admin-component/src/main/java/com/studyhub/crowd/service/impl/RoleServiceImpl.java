package com.studyhub.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.studyhub.crowd.entity.Role;
import com.studyhub.crowd.entity.RoleExample;
import com.studyhub.crowd.mapper.RoleMapper;
import com.studyhub.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haoren
 * @create 2020-12-07 20:04
 */

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        //1.开启分页
        PageHelper.startPage(pageNum, pageSize);

        //2.封装数据
        List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);

        PageInfo<Role> pageInfo = new PageInfo<>(roleList);

        return pageInfo;

    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void removeRoleById(List<Integer> list) {

        //roleMapper.deleteByPrimaryKey(id);

        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();

        criteria.andIdIn(list);

        roleMapper.deleteByExample(roleExample);
    }

    @Override
    public List<Role> getAssignedRoleByAdminId(Integer adminId) {
        return roleMapper.selectAssignedRoleByAdminId(adminId);
    }

    @Override
    public List<Role> getUnassignedRoleByAdminId(Integer adminId) {
        return roleMapper.selectUnassignedRoleByAdminId(adminId);
    }

}
