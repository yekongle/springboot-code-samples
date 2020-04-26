package top.yekongle.pagehelper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import top.yekongle.pagehelper.entity.Product;
import top.yekongle.pagehelper.mapper.ProductMapper;

/** 
* @Description: 页面控制类
* @Author: Yekongle 
* @Date: 2020年4月22日
*/

@Slf4j
@Controller
public class PageController {
	
	@Autowired
	private ProductMapper productMapper;

	/**
	 * async: 是否已加载
	 * pageNum：当前页
	 * pageSize：每页的数量
	 * */
	@GetMapping
	public ModelAndView index(@RequestParam(value = "async", required = false) boolean async,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "1") int pageSize, Model model) {
		log.info("pageNum{}, pageSize{}", pageNum, pageSize);
		// 开始分页
		PageHelper.startPage(pageNum, pageSize);
		List<Product> productList = productMapper.listProduct();
		
		PageInfo page = new PageInfo(productList);
		model.addAttribute("page", page);
		
		return new ModelAndView(async == true ? "index :: #mainContainerRepleace" : "index", "userModel",
				model);
	}
}
