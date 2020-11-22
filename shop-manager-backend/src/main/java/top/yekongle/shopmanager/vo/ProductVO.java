package top.yekongle.shopmanager.vo;

import lombok.Data;

/**
 * @author Yekongle
 * @date 2020/10/27 22:20
 */
@Data
public class ProductVO {
    private Integer id;

    private String name;

    private Double price;

    private int onSale;

    private String coverImg;

    private String content;

}
