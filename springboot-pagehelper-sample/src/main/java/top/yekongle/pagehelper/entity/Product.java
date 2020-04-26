package top.yekongle.pagehelper.entity;

import lombok.Data;

/** 
* @Description: 
* @Author: Yekongle 
* @Date: 2020年4月22日
*/
@Data
public class Product {
	private Integer id;
	private String category;
	private String name;
	private String discount;
	private double price;
}
