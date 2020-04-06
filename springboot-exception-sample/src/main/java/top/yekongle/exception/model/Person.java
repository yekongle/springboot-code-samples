package top.yekongle.exception.model;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description 业务实体类
 * */
@Data
public class Person {
	@NotEmpty(message = "姓名不能为空!")
	private String name;
	@NotNull(message = "年龄不能为空!")
	private Integer age;
}
