package top.yekongle.security.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** 
* @Description: 
* @Author: Yekongle 
* @Date: 2020年5月17日
*/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("Jack").password("$2a$10$jxi3LTkdNRPMv0Cb6ag9kO/7xYUnkN.9aTKoSag8u3Ep8pBe2rMMm").roles("USER")
			.and()
			.withUser("David").password("$2a$10$jxi3LTkdNRPMv0Cb6ag9kO/7xYUnkN.9aTKoSag8u3Ep8pBe2rMMm").roles("USER")
			.and()
			.withUser("Kevin").password("$2a$10$jxi3LTkdNRPMv0Cb6ag9kO/7xYUnkN.9aTKoSag8u3Ep8pBe2rMMm").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/fonts/**", "/img/**").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/login*", "/logout*").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/home.html")
			.failureUrl("/login?error=true").permitAll()
			.and()
			.logout()
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/logout.html?logSucc=true").permitAll();
	}
	
	/*
	 * public static void main(String[] args) { System.out.println(new
	 * BCryptPasswordEncoder().encode("A123456!")); }
	 */
	
}
