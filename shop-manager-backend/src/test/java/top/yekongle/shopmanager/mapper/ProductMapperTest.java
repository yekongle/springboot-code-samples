package top.yekongle.shopmanager.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.yekongle.shopmanager.ShopManagerBackendApplicationTests;
import top.yekongle.shopmanager.entity.Product;
import top.yekongle.shopmanager.vo.ProductVO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yekongle
 * @date 2020/10/27 22:24
 */
@Slf4j
class ProductMapperTest extends ShopManagerBackendApplicationTests {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void productTest() {
        //参数一是当前页，参数二是每页个数
        IPage<Product> productPage = new Page<>(1, 2);
        productPage = productMapper.selectPage(productPage, null);
        List<Product> productList = productPage.getRecords();
        productList.forEach(e-> log.info(e.toString()));
    }

}