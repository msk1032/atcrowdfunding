package com.studyhub.crowd.mvc.security;

import com.studyhub.crowd.entity.Admin;
import com.studyhub.crowd.entity.Role;
import com.studyhub.crowd.mvc.bo.SecurityAdmin;
import com.studyhub.crowd.service.api.AdminService;
import com.studyhub.crowd.service.api.AuthService;
import com.studyhub.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haoren
 * @create 2020-12-12 12:44
 */

@Component
public class CrowdAdminDetailsService implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AuthService authService;
    @Autowired
    private RoleService roleService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminService.getAdminByLoginAcct(username);
        //1.获取当前登录用户的角色
        List<Role> assignedRoleList = roleService.getAssignedRoleByAdminId(admin.getId());
        //2.获取当前用户拥有的权限
        List<String> authNameList = authService.getAssignedAuthNameByAdminId(admin.getId());
        //3.将用户的角色和权限信息存入
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Role role : assignedRoleList) {
            String roleName = "ROLE_"+role.getName();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
            authorities.add(authority);

        }
        for (String auth : authNameList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(auth);
            authorities.add(authority);
        }

        SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);

        return securityAdmin;
    }
}
