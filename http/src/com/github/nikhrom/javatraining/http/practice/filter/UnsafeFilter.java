package com.github.nikhrom.javatraining.http.practice.filter;

import com.github.nikhrom.javatraining.http.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/admin")
public class UnsafeFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");

        if(Objects.nonNull(user)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            ((HttpServletResponse) servletResponse).sendRedirect("/registration");
        }


    }
}
