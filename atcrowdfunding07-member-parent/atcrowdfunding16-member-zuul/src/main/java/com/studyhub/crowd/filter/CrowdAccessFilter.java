package com.studyhub.crowd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.context.RequestContext;
import com.studyhub.crowd.constant.AccessPassResources;
import com.studyhub.crowd.constant.CrowdConstant;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author haoren
 * @create 2021-01-18 18:22
 */
@Component
public class CrowdAccessFilter extends ZuulFilter {



    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        String servletPath = request.getServletPath();

        if(AccessPassResources.PASS_RES_SET.contains(servletPath)) {
            return false;
        }
        return !AccessPassResources.judgeServletPathIsStaticResources(servletPath);
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpSession session = requestContext.getRequest().getSession();

        Object loginMember = session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!"+loginMember);
        if (loginMember == null) {
            HttpServletResponse response = requestContext.getResponse();

            session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);

            try {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!"+"重定向");
                response.sendRedirect("/auth/member/to/login/page");
            } catch (IOException e) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!"+"异常");
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public String filterType() {

        //在目标微服务调用之前执行过滤
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
