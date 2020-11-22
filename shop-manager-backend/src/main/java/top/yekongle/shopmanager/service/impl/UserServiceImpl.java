package top.yekongle.shopmanager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.yekongle.shopmanager.common.CommonEnum;
import top.yekongle.shopmanager.entity.User;
import top.yekongle.shopmanager.exception.BizException;
import top.yekongle.shopmanager.mapper.UserMapper;
import top.yekongle.shopmanager.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yekongle.shopmanager.utils.JwtTokenUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yekongle
 * @since 2020-10-24
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:" + username);
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }


    @Override
    public String authenUser(User user) {
        try{
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( user.getUsername(), user.getPassword() );
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            final UserDetails userDetails = this.loadUserByUsername( user.getUsername() );
            final String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        } catch (Exception e) {
            log.info("Not found user {}", e.getMessage());
            throw new BizException();
        }
    }

    @Override
    public String logout(User user) {
        return null;
    }
}
