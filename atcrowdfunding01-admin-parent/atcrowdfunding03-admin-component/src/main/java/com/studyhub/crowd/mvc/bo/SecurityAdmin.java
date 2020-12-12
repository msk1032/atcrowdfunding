package com.studyhub.crowd.mvc.bo;

import com.studyhub.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.List;

/**
 * 由于user对象中只有用户名和密码 不能获取其他信息
 * 所以封装一个bo实体类 获取到其他信息
 * @author haoren
 * @create 2020-12-12 12:34
 */
public class SecurityAdmin extends User {
    private static final long serialVersionUID = 1L;

    private Admin originalAdmin;

    public Admin getOriginalAdmin() {
        return originalAdmin;
    }

    public SecurityAdmin(Admin originalAdmin, List<GrantedAuthority> authorities) {

        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);

        this.originalAdmin = originalAdmin;

        // 将原始 Admin 对象中的密码擦除
        this.originalAdmin.setUserPswd(null);


    }
}
