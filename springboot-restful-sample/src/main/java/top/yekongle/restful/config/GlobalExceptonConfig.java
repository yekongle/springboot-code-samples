package top.yekongle.restful.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import top.yekongle.restful.exception.EmployeeNotFoundException;

/**
 * @Description: 全局异常配置
 * @Author: Yekongle
 * @Date: Apr 7, 2020
 */
@ControllerAdvice
public class GlobalExceptonConfig {
	
	/**
	 * @ResponseBody 方法返回结果直接写入到 http reponse中
	 * @ExceptionHandler 捕捉指定的exception
	 * @ResponseStatus 指定response的http status：2xx/4xx/5xx
	 * */
	@ResponseBody
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String employeeNotFoundHandler(EmployeeNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String systemErrorHandler(Exception ex) {
		return ex.getMessage();
	}
}
