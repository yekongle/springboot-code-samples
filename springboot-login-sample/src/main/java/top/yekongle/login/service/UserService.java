package top.yekongle.login.service;

import top.yekongle.login.dto.UserDTO;
import top.yekongle.login.entity.User;
import top.yekongle.login.exception.UserAlreadyExistException;

/** 
* @Description: 用户业务处理接口
* @Author: Yekongle 
* @Date: 2020年5月5日
*/
public interface UserService {
	User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException;
}
