package top.yekongle.login.security;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import top.yekongle.login.repository.UserAuthorityRepository;
import top.yekongle.login.repository.UserRepository;
import top.yekongle.login.entity.User;
import top.yekongle.login.entity.UserAuthority;
/** 
* @Description: 
* @Author: Yekongle 
* @Date: 2020年5月5日
*/

@Slf4j
@Service("userDetailsService")
@Transactional
public class MyUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAuthorityRepository userAuthorityRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("Email:" + email);
		User user = userRepository.findByEmail(email);

		if (user == null) {
            throw new UsernameNotFoundException("找不到该用户: "+ email);
        }
		boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        return new org.springframework.security.core.userdetails
        		.User(user.getEmail(), user.getPassword(), enabled, accountNonExpired
        				, credentialsNonExpired, accountNonLocked,	getAuthorities(user.getEmail()));
	}
	
	private List<GrantedAuthority> getAuthorities (String username) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<UserAuthority> userAuthorityList = userAuthorityRepository.findByUsername(username);
        log.info("role size:" + userAuthorityList.size());
        for (UserAuthority userAuthority : userAuthorityList) {
            authorities.add(new SimpleGrantedAuthority(userAuthority.getRole()));
        }
        return authorities;
    }
}
