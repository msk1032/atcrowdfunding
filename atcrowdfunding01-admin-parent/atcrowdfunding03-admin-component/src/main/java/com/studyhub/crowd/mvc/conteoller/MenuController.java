package com.studyhub.crowd.mvc.conteoller;

import com.studyhub.crowd.entity.Menu;
import com.studyhub.crowd.service.api.MenuService;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author haoren
 * @create 2020-12-08 23:04
 */

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getWholeTree() {
        //1.查询全部menu对象
        List<Menu> menuList = menuService.getAll();

        Menu root = null;
        Map<Integer, Menu> menuMap = new HashMap<Integer, Menu>();
        // 2.将集合中的元素放入map中
        for (Menu menu : menuList) {
            Integer id = menu.getId();
            menuMap.put(id, menu);
        }
        //3.遍历集合 找到每个元素的关系
        for (Menu menu: menuList) {
            Integer pid = menu.getPid();
            //设置根节点
            if (pid == null) {
                root = menu;
                continue;
            }
            //获取此元素的父节点
            Menu father = menuMap.get(pid);
            //将此元素添加到父节点的后代中
            father.getChildren().add(menu);

        }
        //4.返回根节点
        return ResultEntity.successWithData(root);
    }


    @RequestMapping("/menu/save.json")
    public ResultEntity<String> saveMenu(Menu menu) {

        menuService.saveMenu(menu);

        return ResultEntity.successWithoutData();
    }


    @RequestMapping("/menu/update.json")
    public ResultEntity<String> updateMenu(Menu menu) {

        menuService.updateMenu(menu);

        return ResultEntity.successWithoutData();
    }


    @RequestMapping("menu/remove.json")
    public ResultEntity<String> removeMenu(Integer id){

        menuService.removeMenuById(id);
        return ResultEntity.successWithoutData();
    }
}
