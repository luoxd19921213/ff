package com.yvdedu.guan.tool;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyExceptionHandel extends AbstractHandlerExceptionResolver{
	//@PostConstruct//这个注解是让这个类初始化完成后调用   ,@PreDestroy这个是准备摧毁的时候调用
//这个类是专门用来做异常处理的  但是只是限于用ajax返回的   （需要在mvc-servlet.xml中配置）
	@Override
	protected ModelAndView doResolveException(HttpServletRequest req,
			HttpServletResponse resp, Object arg2, Exception ex) {
		if(ex instanceof BindException){
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			BindException bt = (BindException) ex;
			ObjectMapper om = new ObjectMapper();
				try {
					resp.getWriter().write(om.writeValueAsString(bt.getFieldErrors()));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return new ModelAndView();
	}

}
