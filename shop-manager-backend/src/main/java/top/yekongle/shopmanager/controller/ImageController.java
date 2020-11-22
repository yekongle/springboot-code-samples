package top.yekongle.shopmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.yekongle.shopmanager.service.IProductService;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Yekongle
 * @date 2020/11/5 1:58
 */
@Slf4j
@Controller
public class ImageController {

    @Autowired
    private IProductService productService;

/*    @GetMapping(value = "/image/{requestPath}", produces = MediaType.IMAGE_JPEG_VALUE)
    public BufferedImage getImage(@PathVariable("requestPath") String requestPath) throws IOException {
        log.info("Request: {}", requestPath);
       // return productService.getUploadImage(request.getRequestURI());
        return null;
    }*/
}
