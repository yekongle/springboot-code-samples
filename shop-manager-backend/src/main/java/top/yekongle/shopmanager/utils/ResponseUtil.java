package top.yekongle.shopmanager.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Yekongle
 * @date 2020/10/28 0:33
 */

public class ResponseUtil {

    public static void handleJsonResponse(HttpServletResponse response, Object obj) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out= null;
        out = response.getWriter();
        out.print(JSON.toJSONString(obj));
        out.flush();
        out.close();
    }
}
