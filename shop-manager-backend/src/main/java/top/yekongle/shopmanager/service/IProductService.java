package top.yekongle.shopmanager.service;

import org.springframework.web.multipart.MultipartFile;
import top.yekongle.shopmanager.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yekongle.shopmanager.vo.PageVO;
import top.yekongle.shopmanager.vo.ProductVO;

import java.awt.image.BufferedImage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yekongle
 * @since 2020-10-24
 */
public interface IProductService extends IService<Product> {
    PageVO<?> queryProducts(Integer pageNum, Integer pageSize);
    String uploadFile(MultipartFile uploadFile);
    BufferedImage getUploadImage(String path);
}
