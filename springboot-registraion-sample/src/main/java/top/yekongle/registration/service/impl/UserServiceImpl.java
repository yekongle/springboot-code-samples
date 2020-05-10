package top.yekongle.registration.service.impl;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yekongle.registration.dto.UserDTO;
import top.yekongle.registration.entity.User;
import top.yekongle.registration.exception.UserAlreadyExistException;
import top.yekongle.registration.repository.UserRepository;
import top.yekongle.registration.service.UserService;

/** 
* @Description: 用户业务处理实现
* @Author: Yekongle 
* @Date: 2020年5月5日
*/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException {
		if (emailExists(userDTO.getEmail())) {   
            throw new UserAlreadyExistException("该邮箱已被注册:" + userDTO.getEmail());
        }
		
		User user = new User(); 
		user.setEmail(userDTO.getEmail());
		user.setRoles(Arrays.asList("ROLE_USER"));
		
		return userRepository.save(user);
	} 

	private boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}
}
