/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.interceptor;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.BasicConfigurator;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author dedhi
 */
public class ScriptingInterceptor extends HandlerInterceptorAdapter{
    private static Logger logger = Logger.getLogger(ScriptingInterceptor.class);
    static{
		BasicConfigurator.configure();
	}
    
    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("Before handling the request");
                Enumeration<String> parameters = request.getParameterNames();
                while(parameters.hasMoreElements()){
                    String paramName = parameters.nextElement();
                    String paramValue = request.getParameter(paramName);
                    String updatedParamValue = cleanXSS(paramValue);
                    request.setAttribute(paramName, updatedParamValue);
                }
                return true;
	}
        
       @Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("After handling the request");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("After rendering the view");
		super.afterCompletion(request, response, handler, ex);
	}
        
        private String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		logger.info("InnXSS RequestWrapper ..............." + value);
		//value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		//value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		//value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
//		value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
//		value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
		value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
		value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
               value=value.replaceAll("'\\s*(and|or|xor|OR|&&|\\|\\|)\\s*('|[0-9]|`?[a-z\\._-]+`?\\s*=|[a-z]+\\s*\\()", "");
               // value=value.replaceAll("/[\\t\\r\\n]|(--[^\\r\\n]*)|(\\/\\*[\\w\\W]*?(?=\\*)\\*\\/)/gi", "");
                ///[\t\r\n]|(--[^\r\n]*)|(\/\*[\w\W]*?(?=\*)\*\/)/gi
		value = value.replaceAll("<script.*?>", "");
		value = value.replaceAll("</script.*?>", "");
		logger.info("OutnXSS RequestWrapper ........ value ......." + value);
		return value;
	}
}
