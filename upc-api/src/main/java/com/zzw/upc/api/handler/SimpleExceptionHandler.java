package com.zzw.upc.api.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zzw.upc.api.exception.AccessForbiddenException;

@ControllerAdvice(annotations = { Controller.class })
public class SimpleExceptionHandler {

	private static final Logger logger = Logger.getLogger(SimpleExceptionHandler.class);

	// 通用异常的处理，返回500
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String simpleExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logger.error(request.getRequestURL());
		logger.error(e.getMessage(), e);
		return "error";
	}

	// 空结果集
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	public String notFoundExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logger.error(request.getRequestURL());
		logger.error(e.getMessage(), e);
		return "error";
	}

	// 禁止访问
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = { AccessForbiddenException.class })
	public String forbiddenExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logger.error(request.getRequestURL());
		logger.error(e.getMessage(), e);
		return "error";
	}

}
