package com.studyhub.crowd.service.api;

import com.studyhub.crowd.entity.Admin;

import java.util.List;

/**
 * @author haoren
 * @create 2020-11-30 13:59
 */
public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAll();
}
