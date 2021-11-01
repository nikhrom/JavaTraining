package com.github.nikhrom.javatraining.http.practice.filter;


import com.github.nikhrom.javatraining.http.practice.util.UrlPath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static com.github.nikhrom.javatraining.http.practice.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter{

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN,
            REGISTRATION,
            IMAGES,
            LOCALE);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
        if(isPublicPath(requestUri) || isUserLoggedIn(servletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            ((HttpServletResponse)servletResponse).sendRedirect("/login");
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPublicPath(String requestUri) {
        return PUBLIC_PATH.stream().anyMatch(requestUri::startsWith);
    }
}
