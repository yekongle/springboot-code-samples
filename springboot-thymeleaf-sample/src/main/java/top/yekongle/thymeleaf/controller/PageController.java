package top.yekongle.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.yekongle.thymeleaf.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @GetMapping
    public String index(Model model) {
        List<Product> productList = this.getProductList();
        model.addAttribute("products", productList);
        model.addAttribute("title", "商品列表");
        return "index";
    }

    List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product("手机", "小米10", 3499);
        Product product2 = new Product("手机", "华为P40", 4188);
        Product product3 = new Product("手机", "一加8", 3999);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        return productList;
    }

}
