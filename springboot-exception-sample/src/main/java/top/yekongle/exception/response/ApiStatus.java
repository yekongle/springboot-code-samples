package top.yekongle.exception.response;

/**
 * @Description 自定义返回状态
 */
public enum ApiStatus {

    SUCCESS(200, "Success"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_REQUEST(400, "Bad Request");

    private final int code;
    private final String msg;

    ApiStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Return the integer value of this status code.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getMsg() {
        return this.msg;
    }
}
