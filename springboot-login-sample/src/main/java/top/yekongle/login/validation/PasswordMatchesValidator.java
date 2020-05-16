package top.yekongle.login.validation;

import top.yekongle.login.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/** 
* @Description: 注册密码匹配校验
* @Author: Yekongle 
* @Date: 2020年5月6日
*/
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		UserDTO user = (UserDTO) value;
		return user.getPassword().equals(user.getMatchingPassword());
	}
	
}
