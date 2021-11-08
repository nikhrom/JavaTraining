package com.github.nikhrom.javatraining.http.practice.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;

@WebFilter("/*")
public class LoginFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.getParameterMap()
                .forEach((k, v) -> System.out.println(String.format("%s: %s", k, Arrays.toString(v))));

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
