package top.yekongle.registration.validation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import com.google.common.base.Joiner;

import lombok.extern.slf4j.Slf4j;
/** 
* @Description: 根据自定义规则校验密码，使用了 passay 密码库
* @Author: Yekongle 
* @Date: 2020年5月9日
*/
@Slf4j
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {

		// 实现自定义的错误消息
		URL resource = this.getClass().getClassLoader().getResource("passay-messages.properties");
		Properties props = new Properties();
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(resource.getPath()), "UTF-8");
			props.load(isr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		MessageResolver resolver = new PropertiesMessageResolver(props);
		
		PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(
				// 密码长度 6-18
                new LengthRule(6, 18), 
                // 不允许有空格
                new WhitespaceRule(),
				// 至少有一个字母大写
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				// 至少有一个数字
				new CharacterRule(EnglishCharacterData.Digit, 1),
				// 至少有一个特殊字符
				new CharacterRule(EnglishCharacterData.Special, 1)
				));
		
		// 校验密码结果
		RuleResult result = validator.validate(new PasswordData(password));
		log.info("Result:" + validator.getMessages(result));
		if(result.isValid()) {
	         return true;
		} 
		// 禁止默认的校验约束
        context.disableDefaultConstraintViolation();
        // 根据自定义错误信息创建约束
        context.buildConstraintViolationWithTemplate(Joiner.on(",").join(validator.getMessages(result))).addConstraintViolation();
        
		return false;
	}
	
}
