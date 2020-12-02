package com.studyhub.crowd.mvc.config;

import com.google.gson.Gson;
import com.studyhub.crowd.constant.CrowdConstant;
import com.studyhub.crowd.exception.AccessForbiddenException;
import com.studyhub.crowd.exception.LoginFailedException;
import com.studyhub.crowd.utils.CrowdUtils;
import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haoren
 * @create 2020-12-01 13:12
 */

@ControllerAdvice //表示当前类是一个基于注解的异常处理类
public class CrowdExceptionResolver {

    /**
     * 处理没有权限访问的异常 要登录
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolveAccessForbiddenException(AccessForbiddenException exception, HttpServletRequest request,
                                                    HttpServletResponse response )  throws IOException {
        return commonResolve(exception,request, response, "admin-login");


    }

    /**
     * 处理登陆失败的异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(LoginFailedException exception, HttpServletRequest request,
                                                    HttpServletResponse response )  throws IOException {
        return commonResolve(exception,request, response, "admin-login");


    }

    //@ExceptionHandler 将一个具体的异常和方法关联起来
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(NullPointerException exception, HttpServletRequest request,
                                                    HttpServletResponse response )  throws IOException {
        return commonResolve(exception,request, response, "system-error");


    }

    private ModelAndView commonResolve(Exception exception, HttpServletRequest request, HttpServletResponse response, String viewName) throws IOException {

        //1.判断当前请求类型
        boolean requestType = CrowdUtils.isAjaxRequest(request);

        //2.如果时ajax请求 返回null
        if(requestType) {
            //获取响应的消息体
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            //通过response将消息回显给浏览器
            response.getWriter().write(json);
            //不需要提供ModelAndView对象
            return null;
        }

        //如果不是ajax请求 创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);

        //设置对应的视图
        modelAndView.setViewName(viewName);

        return modelAndView;
    }


}
