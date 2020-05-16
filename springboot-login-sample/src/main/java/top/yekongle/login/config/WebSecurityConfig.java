	package top.yekongle.login.config;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.http.HttpMethod;
	import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
	import org.springframework.security.core.session.SessionRegistry;
	import org.springframework.security.core.session.SessionRegistryImpl;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.authentication.AuthenticationFailureHandler;
	import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

	import org.springframework.security.web.session.HttpSessionEventPublisher;
	import top.yekongle.login.security.MyLogoutSuccessHandler;
	import top.yekongle.login.security.MyUserDetailServiceImpl;

	/**
	* @Description: Web 安全配置
	* @Author: Yekongle
	* @Date: 2020年5月5日
	*/
	@Configuration
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private MyUserDetailServiceImpl userDetailsService;

		@Autowired
		private AuthenticationSuccessHandler authenticationSuccessHandler;

		@Autowired
		private AuthenticationFailureHandler authenticationFailureHandler;

		@Autowired
		private MyLogoutSuccessHandler myLogoutSuccessHandler;

		// 使用 BCrypt强哈希方法来加密密码, 每次加密结果不一样
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}


		/*
		* 可以有多个 AuthenticationProvider，默认使用 DaoAuthenticationProvide
		* DaoAuthenticationProvider 在进行认证的时候需要一个 UserDetailsService 来获取用户的信息 UserDetails
		* 其中包括用户名、密码和所拥有的权限
		* 当其中一个 AuthenticationProvider 认证成功后，后续 provider不再认证
		* */
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
			authenticationProvider.setUserDetailsService(userDetailsService);
			authenticationProvider.setPasswordEncoder(passwordEncoder());
			return authenticationProvider;
		}

		/* 注册 session 创建和销毁监听器，以便用于支持 session 并发控制
		 * 通知 Spring Security 更新会话注册表
		 * 实际上创建的监听只使用销毁事件
		 **/
		@Bean
		public HttpSessionEventPublisher httpSessionEventPublisher() {
			return new HttpSessionEventPublisher();
		}

		// 跟踪活跃的session
		@Bean
		public SessionRegistry sessionRegistry() {
			return new SessionRegistryImpl();
		}

		/*
		 * 用户认证
		 * 这里使用通用的用户认证，还有基于内存的用户和JDBC中的用户
		 * 数据访问方式可以是多种多样，包括非关系型数据库, 这时就需先自定义实现 UserDetailsService 接口来获取用户信息
		 * */
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider());
		}

		/*
		 * 请求授权配置
		 * */
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.authorizeRequests()
				// 允许访问 H2 DB 控制台
				.antMatchers("/h2/**").permitAll()
				.antMatchers("/css/**", "/js/**", "/fonts/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers("/user/registration*", "/registration*", "/successRegister*", "/login*", "/logout*").permitAll()
				.antMatchers("/invalidSession*").anonymous()
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login")
				// 使用自定义登录成功处理
				.successHandler(authenticationSuccessHandler)
				// 使用自定义登录成功处理
				.failureHandler(authenticationFailureHandler)
				.permitAll()
			.and()
				   .sessionManagement()
				   // 无效 session 跳转
				   .invalidSessionUrl("/invalidSession.html")
				   // 确保单个用户的单个账号，只有一个活跃的session
				   .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
				   // 创建一个新的HTTP会话后，使旧的HTTP会话无效，并将旧会话的属性复制过来
				   .sessionFixation().migrateSession()
			.and()
				.logout()
			    // 使用自定义注销登录成功处理
				.logoutSuccessHandler(myLogoutSuccessHandler)
				// 会清空所有已定义的session
				.invalidateHttpSession(false)
				// 删除 cookie
				.deleteCookies("JSESSIONID")
				.permitAll();
		}

	}
