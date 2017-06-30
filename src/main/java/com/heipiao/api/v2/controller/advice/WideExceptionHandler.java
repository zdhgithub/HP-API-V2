package com.heipiao.api.v2.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.exception.ExpectationFailedException;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.exception.PreconditionException;
import com.heipiao.api.v2.exception.ServiceException;
import com.heipiao.api.v2.exception.msg.UniversalErrorMessage;

/**
 * @author Chris
 *
 */
@RestControllerAdvice(basePackages = "com.heipiao.api.v2.controller", annotations = RestController.class)
public class WideExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(WideExceptionHandler.class);
	
	/**
	 * 一般用于参数检查
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public UniversalErrorMessage _400_Handler(BadRequestException e) {
		return new UniversalErrorMessage(e.getCode(), e.getMessage());
	}
	
	/**
	 * 控制器和服务层都有可能抛出
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public UniversalErrorMessage _404_Handler(NotFoundException e) {
		return new UniversalErrorMessage(e.getCode(), e.getMessage());
	}
	
	/**
	 * 受业务流程影响，不能正常运行
	 * @param e
	 * @return
	 */
	@ExceptionHandler(PreconditionException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public UniversalErrorMessage _412_Handler(PreconditionException e) {
		return new UniversalErrorMessage(e.getCode(), e.getMessage());
	}
	
	/**
	 * 业务运行前置依赖异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ExpectationFailedException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public UniversalErrorMessage _417_Handler(ExpectationFailedException e) {
		return new UniversalErrorMessage(e.getCode(), e.getMessage());
	}
	
	/**
	 * 服务异常，通常由服务层抛出
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public UniversalErrorMessage _500_Handler(ServiceException e) {
		return new UniversalErrorMessage(e.getCode(), e.getMessage());
	}
	
	/**
	 * 其他未处理的异常
	 * 
	 * 注意，这个方法有所不同：
	 * 其他都是处理程序自主抛出的异常，关于日志的处理应在服务层完成
	 * 这个方法处理未检测的异常，日志处理需要在这里完成
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public UniversalErrorMessage allHandler(Exception e) {
		logger.error("未经处理的", e);
		
		return new UniversalErrorMessage(503, "服务不可用");
	}

}
