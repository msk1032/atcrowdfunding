package com.studyhub.crowd.service.api;

import com.studyhub.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author haoren
 * @create 2020-12-10 16:19
 */
public interface AuthService {
    List<Auth> getAllAuth();

    List<Integer> getAssignedAuthByRoleId(Integer roleId);

    void saveRoleAuthRelationship(Map<String, List<Integer>> map);

    List<String> getAssignedAuthNameByAdminId(Integer adminId);
}
