package top.yekongle.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description 自定义response实体类
 */
@JsonInclude(Include.NON_NULL)
@Data
@AllArgsConstructor
public class ApiResponse {
	private Integer code;
	private String msg;
	private Object data;
	
	public static ApiResponse success(Object data) {
		return new ApiResponse(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMsg(), data);
	}
	
	public static ApiResponse error(Integer code, String msg) {
		return new ApiResponse(code, msg, null);
	}
}
