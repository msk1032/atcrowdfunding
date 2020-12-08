package com.studyhub.crowd.mvc.conteoller;

import com.github.pagehelper.PageInfo;
import com.studyhub.crowd.entity.Role;
import com.studyhub.crowd.service.api.RoleService;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @author haoren
 * @create 2020-12-07 20:06
 */

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;


    @RequestMapping("/role/get/page.html")
    public String getPageInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                              @RequestParam(value = "keyword", defaultValue = "") String keyword,
                              Model model) {

        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
        model.addAttribute("pageInfo", pageInfo);

        return "role-page";
    }

    @ResponseBody
    @RequestMapping("/role/save.json")
    public ResultEntity<String> saveRole(Role role) {

        roleService.saveRole(role);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("role/update.json")
    public ResultEntity<String> updateRole(Role role) {

        roleService.updateRole(role);

        return ResultEntity.successWithoutData();
    }

    @RequestMapping("/role/remove.html")
    public String removeRole(@RequestParam("list") ArrayList<Integer> delList,
                             @RequestParam("pageNum") Integer pageNum,
                             @RequestParam("keyword") String keyword) {

        for (Integer id : delList) {
            roleService.removeRoleById(id);
        }

        return "redirect:/role/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

}
