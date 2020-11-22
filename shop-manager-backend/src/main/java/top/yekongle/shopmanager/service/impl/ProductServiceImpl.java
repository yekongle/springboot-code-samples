package top.yekongle.shopmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import top.yekongle.shopmanager.common.CommonEnum;
import top.yekongle.shopmanager.entity.Product;
import top.yekongle.shopmanager.exception.BizException;
import top.yekongle.shopmanager.mapper.ProductMapper;
import top.yekongle.shopmanager.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yekongle.shopmanager.vo.PageVO;
import top.yekongle.shopmanager.vo.ProductVO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Yekongle
 * @since 2020-10-24
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Value("${file.upload-path}")
    private String baseUploadPath;

    @Override
    public PageVO<?> queryProducts(Integer pageNum, Integer pageSize) {

        //参数一是当前页，参数二是每页个数
        IPage<Product> productPage = new Page<>(pageNum, pageSize);

        productPage = productMapper.selectPage(productPage, new QueryWrapper<Product>().orderByDesc("id"));
        List<Product> productList = productPage.getRecords();
        productList.forEach(e -> log.info(e.toString()));

        PageVO<Product> productPageVO = new PageVO<>();
        productPageVO.setCurrent(productPage.getCurrent());
        productPageVO.setPageSize(pageSize);
        productPageVO.setTotal(productPage.getTotal());
        productPageVO.setList(productPage.getRecords());

        return productPageVO;
    }

    @Override
    public String uploadFile(MultipartFile uploadFile) {
        String newFileName = null;

        // 判断文件夹是否存在，不存在就创建
        //String uploadPath = "/image/" + LocalDate.now().toString().replace("-", "/") + "/";
        String uploadPath = "/image/";

//         上传到本地磁盘目录
        String newPath = baseUploadPath + uploadPath;

        // 上传到项目static目录下
        try {
            //String newPath = ResourceUtils.getURL("classpath:").getPath() + "static" + uploadPath;
            log.info("newpath: {}", newPath);
            File storeFile = new File(newPath);
            if (!storeFile.exists()) {
                storeFile.mkdirs();
            }

            String originalFileName = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));//获取原始图片的扩展名
            newFileName = UUID.randomUUID() + originalFileName;
            //String newUploadFilePath = newPath + newFileName; //新文件的路径
            File newfile = new File(newPath, newFileName);

            uploadFile.transferTo(newfile);//将传来的文件写入新建的文件
            System.out.println("上传图片成功进行上传文件测试");
        } catch (Exception e) {
            log.error("Upload file fail：", e);
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Upload file fail!");
        }

        return uploadPath + newFileName;
    }

    @Override
    public BufferedImage getUploadImage(String requestPath) {
        String path = baseUploadPath + requestPath;
        try {
            return ImageIO.read(new FileInputStream(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
