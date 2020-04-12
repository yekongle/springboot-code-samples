package top.yekongle.restful.exception;

/**
 * @Description: 自定义employee exception
 * @Author: Yekongle
 * @Date: Apr 7, 2020
 */
public class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Long id) {
		super("Could not find employee " + id);
	}
}
