package com.studyhub.crowd.service.impl;

import com.studyhub.crowd.entity.Admin;
import com.studyhub.crowd.mapper.AdminMapper;
import com.studyhub.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    }
}
