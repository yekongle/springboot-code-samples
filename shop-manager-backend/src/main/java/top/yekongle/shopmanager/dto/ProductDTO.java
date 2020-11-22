package top.yekongle.shopmanager.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yekongle
 * @date 2020/11/2 23:18
 */

@Data
public class ProductDTO implements Serializable {
    private String name;
    private Double price;
    private int onSale;
    private String coverImg;
    private String content;
}
