package com.studyhub.crowd.mvc.conteoller;

import com.studyhub.crowd.entity.Auth;
import com.studyhub.crowd.entity.Role;
import com.studyhub.crowd.service.api.AdminService;
import com.studyhub.crowd.service.api.AuthService;
import com.studyhub.crowd.service.api.RoleService;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

/**
 * @author haoren
 * @create 2020-12-10 10:08
 */

@Controller
public class AssignController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @RequestMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage(@RequestParam("adminId" ) Integer adminId,
                                    Model model) {
        List<Role> assignedRoleList = roleService.getAssignedRoleByAdminId(adminId);
        List<Role> unassignedRoleList = roleService.getUnassignedRoleByAdminId(adminId);

        model.addAttribute("assignedRoleList", assignedRoleList);
        model.addAttribute("unassignedRoleList", unassignedRoleList);

        return "assign-role";
    }

    @RequestMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationship(@RequestParam("adminId")Integer adminId,
                                        @RequestParam("pageNum")Integer pageNum,
                                        @RequestParam("keyword")String keyword,
                                        //允许取消所有管理员的角色
                                        @RequestParam(value = "roleList",required = false)List<Integer> roleList) {
        adminService.saveAdminRoleRelationship(adminId, roleList);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @ResponseBody
    @RequestMapping("/assign/get/all/auth.json")
    public ResultEntity<List<Auth>> getAllAuth() {

        List<Auth> authList = authService.getAllAuth();
        return ResultEntity.successWithData(authList);
    }

    @ResponseBody
    @RequestMapping("/assign/get/assigned/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAssignedAuth(@RequestParam("roleId") Integer roleId) {

        List<Integer> list = authService.getAssignedAuthByRoleId(roleId);

        return ResultEntity.successWithData(list);
    }

    @ResponseBody
    @RequestMapping(value = "/assign/do/role/assign/auth.json", produces = "application/json;charset=UTF-8")
    public ResultEntity<String> saveRoleAuthRelationship(@RequestBody Map<String, List<Integer>> map){

        authService.saveRoleAuthRelationship(map);

        return ResultEntity.successWithoutData();
    }


}
