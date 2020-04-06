package top.yekongle.exception.exception;
/**
 * @Description 自定义异常
 * */
public class RestInputException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RestInputException(String message) {
		super(message);
	}
}
