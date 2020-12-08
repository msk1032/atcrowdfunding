package com.studyhub.crowd.mvc.conteoller;

import com.studyhub.crowd.entity.Menu;
import com.studyhub.crowd.service.api.MenuService;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author haoren
 * @create 2020-12-08 23:04
 */

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getWholeTree() {

    }
}
