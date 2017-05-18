package com.heipiao.api.v2.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.exception.ExpectationFailedException;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.exception.PreconditionException;
import com.heipiao.api.v2.exception.ServiceException;
import com.heipiao.api.v2.exception.msg.UniversalErrorMessage;

@ControllerAdvice
public class WideExceptionHandler {
	
	/**
	 * 所有服务异常，通常由服务层抛出
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public UniversalErrorMessage _500_Handler(ServiceException e) {
		return new UniversalErrorMessage(e.getCode(), e.getMessage());
	}
	
	/**
	 * 所有404异常，控制器和服务层都有可能抛出
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
	 * 受业务流程影响，不能正常运行
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public UniversalErrorMessage _400_Handler(PreconditionException e) {
		return new UniversalErrorMessage(e.getCode(), e.getMessage());
	}
	
	/**
	 * TODO 添加
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ExpectationFailedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public UniversalErrorMessage _417_Handler(ExpectationFailedException e) {
		return new UniversalErrorMessage(e.getCode(), e.getMessage());
	}

}
