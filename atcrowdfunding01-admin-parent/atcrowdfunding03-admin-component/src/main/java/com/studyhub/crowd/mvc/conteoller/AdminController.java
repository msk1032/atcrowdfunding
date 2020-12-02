package com.studyhub.crowd.mvc.conteoller;

import com.studyhub.crowd.constant.CrowdConstant;
import com.studyhub.crowd.entity.Admin;
import com.studyhub.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author haoren
 * @create 2020-12-01 22:39
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPwsd") String userPwsd,
                          HttpServletRequest request) {


        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPwsd);

        request.getSession().setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);

        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session) {
        // 强制 Session 失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }





}
