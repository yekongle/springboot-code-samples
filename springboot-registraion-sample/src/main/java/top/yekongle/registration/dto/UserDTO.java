package top.yekongle.registration.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import top.yekongle.registration.validation.PasswordMatches;
import top.yekongle.registration.validation.ValidPassword;

/**
 * @Description: 用户数据传输类
 * @Data：lombok 插件自动生成 getter/setter 方法
 * @PasswordMatches： 自定义校验注解，检查两次输入密码是否一致
 * @ValidPassword：根据自定义规则校验密码
 * @Author: Yekongle
 * @Date: 2020年5月5日
*/

@Data
@PasswordMatches
public class UserDTO {
	@Email
	@NotEmpty
    private String email;
	@NotEmpty
	@ValidPassword
	private String password;

	private String matchingPassword;
}
