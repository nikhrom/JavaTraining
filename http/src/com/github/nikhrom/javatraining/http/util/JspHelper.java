package com.github.nikhrom.javatraining.http.util;


import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static final String JSP_PATH = "/WEB-INF/jsp/%s.jsp";

    public static String getPath(String jspName){
        return JSP_PATH.formatted(jspName);
    };


}
