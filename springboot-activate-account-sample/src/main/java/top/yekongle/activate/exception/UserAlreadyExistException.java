package top.yekongle.activate.exception;

/** 
* @Description: 自定义用户已存在异常
* @Author: Yekongle 
* @Date: 2020年5月5日
*/
public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistException(String message) {
		super(message);
	}
}