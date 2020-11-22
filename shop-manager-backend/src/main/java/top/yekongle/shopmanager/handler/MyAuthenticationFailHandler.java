package top.yekongle.shopmanager.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import top.yekongle.shopmanager.common.CommonEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yekongle
 * @date 2020/10/27 1:00
 */
@Component
@Slf4j
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler {

    public static final String RETURN_TYPE = "html"; // 登录失败时，用来判断是返回json数据还是跳转html页面

    @Autowired
    private ObjectMapper objectMapper;



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败:" + exception.getMessage());
        log.info("username=>" + request.getParameter("username"));


            Map<String, Object> map = new HashMap<>();
            map.put("code", "用戶不存在");
            map.put("message","登录失败");
            map.put("data",exception.getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(map));

    }
}