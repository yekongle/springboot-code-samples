package top.yekongle.registration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import top.yekongle.registration.dto.UserDTO;

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
