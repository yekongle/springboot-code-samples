package top.yekongle.activate.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description: 验证密码是否符合规则
 * TYPE: 可用于类、接口、注解类型、枚举;
 * FIELD：可用于类属性上
 * ANNOTATION_TYPE: 用于注解声明(应用于另一个注解上)
 * @Author: Yekongle
 * @Date: 2020年5月9日
 */
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
public @interface ValidPassword {
	// 默认返回的 error message
	String message() default "密码无效";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
