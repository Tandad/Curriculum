package com.ss.task.action.auth;

import com.ss.task.action.RootAction;
import com.ss.task.model.UserEntity;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liymm on 2015-01-28.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!handler.getClass().isAssignableFrom(HandlerMethod.class))
            return true;

        UserAuthPassport adminAuth = ((HandlerMethod) handler).getMethodAnnotation(UserAuthPassport.class);

        if (adminAuth == null) {
            adminAuth = ((HandlerMethod)handler).getBeanType().getAnnotation(UserAuthPassport.class);
        }

        if (adminAuth != null && adminAuth.validate())
            return validateAdminAuthority(request, response);

        return true;
    }

    boolean validateAdminAuthority(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        if (user != null)
            return true;

        jumpPage(request, response, RootAction.DEFAULT_ACTION);

        return false;
    }

    void jumpPage(HttpServletRequest request, HttpServletResponse response, String relativeURL) throws Exception {
        String url = request.getSession().getServletContext().getContextPath() + relativeURL;
        response.sendRedirect(url);
    }
}
