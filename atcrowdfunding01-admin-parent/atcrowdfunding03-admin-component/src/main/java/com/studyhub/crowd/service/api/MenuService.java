package com.studyhub.crowd.service.api;

import com.studyhub.crowd.entity.Menu;

import java.util.List;

/**
 * @author haoren
 * @create 2020-12-08 23:02
 */
public interface MenuService {
    List<Menu> getAll();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void removeMenuById(Integer id);
}
