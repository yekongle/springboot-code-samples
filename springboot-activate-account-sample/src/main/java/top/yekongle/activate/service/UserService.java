package top.yekongle.activate.service;

import top.yekongle.activate.dto.UserDTO;
import top.yekongle.activate.entity.PasswordResetToken;
import top.yekongle.activate.entity.User;
import top.yekongle.activate.entity.VerificationToken;
import top.yekongle.activate.exception.UserAlreadyExistException;

import java.util.Optional;

/** 
* @Description: 用户业务处理接口
* @Author: Yekongle 
* @Date: 2020年5月5日
*/
public interface UserService {
	User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException;

	void createVerificationToken(User user, String token);

	VerificationToken getVerificationToken(String verificationToken);

	VerificationToken generateNewVerificationToken(String token);

	void saveRegisteredUser(User user);

	User getUser(String verificationToken);

	PasswordResetToken getPasswordResetToken(String token);

	User findUserByEmail(String email);

	void createPasswordResetTokenForUser(User user, String token);

	String validatePasswordResetToken(String token);

	void changeUserPassword(User user, String password);

	Optional<User> getUserByPasswordResetToken(String token);
}
