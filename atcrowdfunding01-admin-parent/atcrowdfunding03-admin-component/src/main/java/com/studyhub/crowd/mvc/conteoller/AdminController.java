package com.studyhub.crowd.mvc.conteoller;

import com.github.pagehelper.PageInfo;
import com.studyhub.crowd.constant.CrowdConstant;
import com.studyhub.crowd.entity.Admin;
import com.studyhub.crowd.service.api.AdminService;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 处理登录请求
     * @param loginAcct
     * @param userPwsd
     * @param request
     * @return
     */
    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPwsd") String userPwsd,
                          HttpServletRequest request) {


        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPwsd);

        request.getSession().setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);

        return "redirect:/admin/to/main/page.html";
    }

    /**
     * 处理登出请求
     * @param session
     * @return
     */
    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session) {
        // 强制 Session 失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

    /**
     * 分页获取 admin数据 默认查询关键字为 空串
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param modelMap
     * @return
     */
    @RequestMapping("/admin/get/page.html")
    public String getAdminPage(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                               ModelMap modelMap) {

        PageInfo<Admin> pageInfo = adminService.getAdminPage(keyword, pageNum, pageSize);

        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);

        return "pages/admin-page";
    }

    /**
     * 删除管理员数据
     * @param adminId
     * @param pageNum
     * @param keyword
     * @return
     */
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String removeAdmin(@PathVariable("adminId") Integer adminId,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("keyword") String keyword){
        adminService.removeAdmin(adminId);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;

    }

    /**
     * 多条记录删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("admin/remove/admins.json")
    public ResultEntity<String> removeAdmins(@RequestParam("ids") String ids) {

        String [] idList = ids.split(",");

        for(int i = 0; i < idList.length; i++) {

            adminService.removeAdmin(Integer.parseInt(idList[i]));
        }


        return ResultEntity.successWithoutData();
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @RequestMapping("/admin/save.html")
    public String saveAdmin(Admin admin) {

        adminService.saveAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }

    /**
     * 获取表格中选中管理员的数据 在更新页面上回显数据
     * @param id
     * @param pageNum
     * @param keyword
     * @param modelMap
     * @return
     */
    @RequestMapping("/admin/to/edit/page.html")
    public String toEditPAge(@RequestParam("adminId") Integer id,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "keyword", defaultValue = "") String keyword,
                             ModelMap modelMap){

        Admin admin = adminService.getAdminById(id);

        modelMap.addAttribute("admin", admin);


        return "admin/admin-edit";
    }

    @RequestMapping("/admin/update.html")
    public String updateAdmin(Admin admin,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        

        adminService.updateAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;

    }



}
