package top.yekongle.shopmanager.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yekongle
 * @date 2020/10/24 18:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static CommonResult success(Object data) {
        return new CommonResult(CommonEnum.SUCCESS.getResultCode()
                , CommonEnum.SUCCESS.getResultMsg(), data);
    }

    /**
     * 成功
     *
     * @return
     */
    public static CommonResult success() {
        return success(null);
    }

    /**
     * 失败
     */
    public static CommonResult error(BaseErrorInfoInterface errorInfo) {
        return new CommonResult(errorInfo.getResultCode()
                , errorInfo.getResultMsg(), null);
    }

    /**
     * 失败
     */
    public static CommonResult error(Integer code, String message) {
        return new CommonResult(code, message);
    }


}
