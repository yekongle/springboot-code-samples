package top.yekongle.thymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String category;
    private String name;
    private double price;
}
