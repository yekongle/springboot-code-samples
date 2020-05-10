package top.yekongle.registration.service;

import top.yekongle.registration.dto.UserDTO;
import top.yekongle.registration.entity.User;
import top.yekongle.registration.exception.UserAlreadyExistException;

/** 
* @Description: 用户业务处理接口
* @Author: Yekongle 
* @Date: 2020年5月5日
*/
public interface UserService {
	User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException;
}
