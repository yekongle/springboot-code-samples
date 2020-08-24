package top.yekongle.activate.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import top.yekongle.activate.dto.UserDTO;
import top.yekongle.activate.entity.PasswordResetToken;
import top.yekongle.activate.entity.User;
import top.yekongle.activate.entity.UserAuthority;
import top.yekongle.activate.entity.VerificationToken;
import top.yekongle.activate.exception.UserAlreadyExistException;
import top.yekongle.activate.repository.PasswordResetTokenRepository;
import top.yekongle.activate.repository.UserAuthorityRepository;
import top.yekongle.activate.repository.UserRepository;
import top.yekongle.activate.repository.VerificationTokenRepository;
import top.yekongle.activate.service.UserService;

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
	private VerificationTokenRepository tokenRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;

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

	@Override
	public VerificationToken getVerificationToken(String verificationToken) {
		return tokenRepository.findByToken(verificationToken);
	}

	@Override
	public VerificationToken generateNewVerificationToken(String token) {
		VerificationToken vToken = tokenRepository.findByToken(token);
		vToken.updateToken(UUID.randomUUID()
				.toString());
		vToken = tokenRepository.save(vToken);
		return vToken;
	}

	@Override
	public void saveRegisteredUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(token, user);
		tokenRepository.save(myToken);
	}

	@Override
	public User getUser(String verificationToken) {
		User user = tokenRepository.findByToken(verificationToken).getUser();
		return user;
	}

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		return passwordTokenRepository.findByToken(token);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordTokenRepository.save(myToken);
	}

	public String validatePasswordResetToken(String token) {
		final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

		return !isTokenFound(passToken) ? "invalidToken"
				: isTokenExpired(passToken) ? "expired"
				: null;
	}

	@Override
	public void changeUserPassword(User user, String password) {
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
	}

	@Override
	public Optional<User> getUserByPasswordResetToken(String token) {
		return Optional.ofNullable(passwordTokenRepository.findByToken(token).getUser());
	}

	private boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	private boolean isTokenFound(PasswordResetToken passToken) {
		return passToken != null;
	}

	private boolean isTokenExpired(PasswordResetToken passToken) {
		final Calendar cal = Calendar.getInstance();
		return passToken.getExpiryDate().before(cal.getTime());
	}


}
