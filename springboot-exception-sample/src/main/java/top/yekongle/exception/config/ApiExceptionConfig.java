package top.yekongle.exception.config;

import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import top.yekongle.exception.exception.RestInputException;
import top.yekongle.exception.response.ApiResponse;
import top.yekongle.exception.response.ApiStatus;

/**
 * @Description 全局异常配置类
 * @Slf4j lombok 注解，自动生成 logger
 * */
@Slf4j
@ControllerAdvice
public class ApiExceptionConfig {

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponse MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
	    	/*String message = null;
	    	for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
				if (message == null) {
					message = objectError.getDefaultMessage();
				} else {
					message = message + objectError.getDefaultMessage();
				}
			}*/
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.info("Bad request: [{}]", message);
        return ApiResponse.error(ApiStatus.BAD_REQUEST.getCode(), message);
    }

    @ExceptionHandler(RestInputException.class)
    @ResponseBody
    public ApiResponse handleRestInputException(RestInputException e) {
        log.info("Business error: [{}]", e.getMessage());
        return ApiResponse.error(ApiStatus.BAD_REQUEST.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse handleExceptions(Exception e) {
        log.error("System error", e);
        return ApiResponse.error(ApiStatus.INTERNAL_SERVER_ERROR.getCode(), ApiStatus.INTERNAL_SERVER_ERROR.getMsg());
    }
}
