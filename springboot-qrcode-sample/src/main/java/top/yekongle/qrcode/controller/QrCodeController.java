package top.yekongle.qrcode.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.yekongle.qrcode.util.QrCodeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/qrCode")
public class QrCodeController {
	  /**
     * @url:  http://127.0.0.1:8080/qrCode/getCommonQRCode?url=https://blog.csdn.net/qq_23483671
     * @desc: 生成普通二维码
     */
    @GetMapping(value = "/getCommonQRCode")
    public void getCommonQRCode(@RequestParam(value = "url") String url, HttpServletResponse response) throws Exception {
    	log.debug("url:" + url);
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            //使用工具类生成不带logo二维码
            QrCodeUtil.encode(url, stream);
        } finally {
            //关闭流操作
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * @url : http://127.0.0.1:8080/qrCode/getQRCodeWithLogo?url=https://blog.csdn.net/qq_23483671
     * @desc: 生成 带有logo二维码
     */
    @GetMapping(value = "/getQRCodeWithLogo")
    public void getQRCodeWithLogo(@RequestParam(value = "url") String url, HttpServletResponse response) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String path = ResourceUtils.getURL("classpath:").getPath();
            log.debug("项目logo资源classpath ： {}", path);
            //logo图片地址
            String  logoPath= ResourceUtils.getURL("classpath:").getPath()+"static/images/"+"logo.png";
            log.debug("logo完整url地址：{}", logoPath);
            //使用工具类生成带logo二维码
            QrCodeUtil.encode(url, logoPath, stream, true);
        } finally {
            //关闭流操作
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
 
}
