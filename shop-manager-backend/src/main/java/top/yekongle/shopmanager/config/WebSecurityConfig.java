package top.yekongle.shopmanager.config;

/**
 * @author Yekongle
 * @date 2020/10/24 18:55
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.yekongle.shopmanager.filter.JwtTokenFilter;
import top.yekongle.shopmanager.handler.MyAuthenticationFailHandler;
import top.yekongle.shopmanager.service.impl.UserServiceImpl;

/**
 * 这是一个高度综合的配置类，主要是通过重写 WebSecurityConfigurerAdapter
 * 的部分 configure配置，来实现用户自定义的部分
 * */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MyAuthenticationFailHandler authenticationFailHandler;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( userService ).passwordEncoder( new BCryptPasswordEncoder() );
    }

    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {

        httpSecurity.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/image/**", "/").permitAll()
                .antMatchers("/static/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                    .failureHandler(authenticationFailHandler);

        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }

    public static void main(String[] args) {
        String password ="123";
        String encrypted = new BCryptPasswordEncoder().encode(password);
        System.out.println(encrypted);
    }

}

