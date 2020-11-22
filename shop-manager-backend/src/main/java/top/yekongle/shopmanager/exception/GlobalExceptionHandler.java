package top.yekongle.shopmanager.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yekongle.shopmanager.common.CommonEnum;
import top.yekongle.shopmanager.common.CommonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yekongle
 * @date 2020/10/25 15:28
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public CommonResult bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return CommonResult.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public CommonResult exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return CommonResult.error(CommonEnum.BODY_NOT_MATCH);
    }

    @ExceptionHandler(value =UsernameNotFoundException.class)
    @ResponseBody
    public CommonResult userExceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return CommonResult.error(400, "用戶不存在");
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public CommonResult exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return CommonResult.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }
}