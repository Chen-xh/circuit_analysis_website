package com.turing.circuit_analysis_website.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CHEN
 * @date 2020/10/20  16:08
 * role[admin,manager]
 * 配置成这样，那用户必须同时具备admin和manager权限才能访问，显然这个是不合理的
 * 自己实现一个role filter，只要任何一个角色符合条件就通过
 */
public class AnyRolesAuthorizationFilter  extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray == null || rolesArray.length == 0) { //没有角色限制，有权限访问
            return true;
        }
        for (String role : rolesArray) {
            if (subject.hasRole(role)) //若当前用户是rolesArray中的任何一个，则有权限访问
                return true;
        }
        return false;
    }
    /**
     * 权限校验失败，错误处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
//        HttpServletResponse httpResponse = WebUtils.toHttp(response);
//        httpResponse.setCharacterEncoding("UTF-8");
//        httpResponse.setContentType("application/json;charset=utf-8");
//        httpResponse.setStatus(HttpStatus.SC_UNAUTHORIZED);
        return false;
    }
}
