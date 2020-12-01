package com.studyhub.crowd.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haoren
 * @create 2020-12-01 12:45
 */
public class CrowdUtils {
    /**
     * 判断当前请求是否位ajax请求 如果是 返回true
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {

        String acceptInfo = request.getHeader("Accept");
        String xRequestInfo = request.getHeader("X-Request-With");

        return (acceptInfo != null && acceptInfo.contains("application/json")) || (xRequestInfo != null && "XMLHttpRequest".equals(xRequestInfo));
    }
}
