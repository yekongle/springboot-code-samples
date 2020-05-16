package top.yekongle.login.service.impl;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import top.yekongle.login.dto.UserDTO;
import top.yekongle.login.entity.User;
import top.yekongle.login.entity.UserAuthority;
import top.yekongle.login.exception.UserAlreadyExistException;
import top.yekongle.login.repository.UserAuthorityRepository;
import top.yekongle.login.repository.UserRepository;
import top.yekongle.login.service.UserService;

/**
 * @Description:
 * @Author: Yekongle
 * @Date: 2020年5月5日
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAuthorityRepository userAuthorityRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException {
		if (emailExists(userDTO.getEmail())) {
			throw new UserAlreadyExistException("该邮箱已被注册:" + userDTO.getEmail());
		}
		log.info("UserDTO:" + userDTO.toString());
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userRepository.save(user);

		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setUsername(userDTO.getEmail());
		userAuthority.setRole("ROLE_USER");
		userAuthorityRepository.save(userAuthority);

		return user;
	}

	private boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}
}
