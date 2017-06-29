package com.heipiao.api.v2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 统一日志记录
 * @author Chris
 *
 */
public class WorkLog extends HandlerInterceptorAdapter {

//	private static final Logger logger = LoggerFactory.getLogger(WorkLog.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		// 这里暂时注释，因为会受WideExceptionHandler影响，异常并不会在这里出现
//		if (e != null) {
//			String uri = request.getRequestURI();
//			String method = request.getMethod();
//			logger.error("控制器异常，URI={}，METHOD={}", uri, method, e);
//		}
	}

}
