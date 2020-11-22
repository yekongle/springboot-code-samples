package top.yekongle.shopmanager.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import top.yekongle.shopmanager.common.CommonResult;
import top.yekongle.shopmanager.dto.ProductDTO;
import top.yekongle.shopmanager.entity.Product;
import top.yekongle.shopmanager.service.IProductService;
import top.yekongle.shopmanager.vo.PageVO;
import top.yekongle.shopmanager.vo.ProductVO;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yekongle
 * @since 2020-10-24
 */
@Slf4j
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public CommonResult<?> getProducts(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
            , @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        PageVO pageVO = productService.queryProducts(pageNum, pageSize);
        return CommonResult.success(pageVO);
    }

    @PostMapping("/products")
    public CommonResult<?> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        productService.save(product);
        return CommonResult.success();
    }

    @GetMapping("/products/{id}")
    public CommonResult<?> getProduct(@PathVariable("id") Integer id) {
        Product product = productService.getById(id);
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(product, productVO);
        return CommonResult.success(productVO);
    }

    @PutMapping("/products/{id}")
    public CommonResult<?> getProduct(@PathVariable("id") Integer id, @RequestBody ProductDTO productDTO) {
        log.info("ProductDTO: {}", productDTO.toString());
        Product product = productService.getById(id);
        BeanUtils.copyProperties(productDTO, product);
        productService.updateById(product);
        return CommonResult.success();
    }

    @DeleteMapping("/products/{id}")
    public CommonResult<?> deleteProduct(@PathVariable("id") Integer id) {
        productService.removeById(id);
        return CommonResult.success();
    }

    @PostMapping("/products/file_upload")
    public CommonResult<?> uploadProductPic(@RequestParam("file") MultipartFile file) {
        String uploadPath = productService.uploadFile(file);
        log.info("Upload path: {}", uploadPath);
        return CommonResult.success(uploadPath);
    }

}

