package top.yekongle.exception.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 * 请求实体
 * @Data lombok 注解，自动生成 getter setter 方法
 * @NotEmpty 参数校验注解
 * */
@Data
public class TestDto {
	@NotEmpty(message = "名字不能为空!")
	private String name;
	@NotEmpty(message = "地址不能为空!")
	private String address;
}
