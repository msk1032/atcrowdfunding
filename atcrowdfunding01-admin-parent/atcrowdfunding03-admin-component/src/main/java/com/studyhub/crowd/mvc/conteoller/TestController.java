package com.studyhub.crowd.mvc.conteoller;

import com.studyhub.crowd.entity.Admin;
import com.studyhub.crowd.service.api.AdminService;
import com.studyhub.crowd.utils.CrowdUtils;
import com.studyhub.crowd.utils.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author haoren
 * @create 2020-11-30 17:32
 */

@Controller
public class TestController {
    @Autowired
    private AdminService adminService;

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @ResponseBody
    @RequestMapping("/send/array/json.json")
    public ResultEntity testReceiveArrayJson(@RequestBody List<Integer> list) {


        for (Integer integer : list) {
            logger.info("num      "+integer);
        }
        String a = null;
        System.out.println(a.length());

        return ResultEntity.successWithData(list);
    }

    @RequestMapping("/send/array.html")
    public String testReceiveArrayOne(@RequestParam("array[]")List<Integer> list) {

        for (Integer integer : list) {
            System.out.println(integer);
        }


        return "target";
    }


    @RequestMapping("/test/ssm.html")
    public String testSsm(Model model, HttpServletRequest request) {
        List<Admin> admins = adminService.getAll();
        boolean ajaxRequest = CrowdUtils.isAjaxRequest(request);

        model.addAttribute("admins", admins);

        System.out.println(12/0);

        return "target";
    }




}
