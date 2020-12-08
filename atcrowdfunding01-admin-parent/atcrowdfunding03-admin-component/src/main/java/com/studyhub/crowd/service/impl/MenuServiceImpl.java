package com.studyhub.crowd.service.impl;

import com.studyhub.crowd.entity.Menu;
import com.studyhub.crowd.entity.MenuExample;
import com.studyhub.crowd.mapper.MenuMapper;
import com.studyhub.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haoren
 * @create 2020-12-08 23:03
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }
}
