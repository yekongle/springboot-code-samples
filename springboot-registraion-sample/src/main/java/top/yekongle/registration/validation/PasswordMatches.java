package top.yekongle.registration.validation;

import java.lang.annotation.Documented;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
/** 
* @Description: TYPE: 可用于类、接口、注解类型、枚举; ANNOTATION_TYPE: 用于注解声明(应用于另一个注解上)
* @Author: Yekongle 
* @Date: 2020年5月6日
*/
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({TYPE, ANNOTATION_TYPE})
public @interface PasswordMatches {
	// 默认返回的 error message
	String message() default "密码不一致";
	// 
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
