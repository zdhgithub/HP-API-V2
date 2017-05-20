package com.heipiao.api.v2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 统一日志记录
 * @author Chris
 *
 */
public class WorkLog extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WorkLog.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		if (e != null) {
			String uri = request.getRequestURI();
			String method = request.getMethod();
			logger.error("控制器异常，URI={}，METHOD={}", uri, method, e);
		}
	}

}
