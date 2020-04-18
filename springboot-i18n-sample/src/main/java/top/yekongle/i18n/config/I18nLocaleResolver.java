package top.yekongle.i18n.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import lombok.extern.slf4j.Slf4j;

/** 
* Description: 区域解析器, 根据请求的值来返回对应的Locale
* Author: Yekongle  
*/
@Slf4j
public class I18nLocaleResolver implements LocaleResolver {
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String l = request.getParameter("l");
		Locale locale = Locale.getDefault();
		if (!StringUtils.isEmpty(l)) {
			String[] split = l.split("_");
			locale = new Locale(split[0], split[1]);
		}
		log.info("Local Country: {}, language: {}", locale.getCountry(), locale.getLanguage());
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

	}

}
