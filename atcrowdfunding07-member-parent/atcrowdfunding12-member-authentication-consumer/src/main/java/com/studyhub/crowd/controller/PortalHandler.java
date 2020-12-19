package com.studyhub.crowd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author haoren
 * @create 2020-12-18 22:59
 */

@Controller
public class PortalHandler {

    @RequestMapping("/")
    public String showPortalPage() {
        // 这里实际开发中需要加载数据……


        return "portal";
    }
}
