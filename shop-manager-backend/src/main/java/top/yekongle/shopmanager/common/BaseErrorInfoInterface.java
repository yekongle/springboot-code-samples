package top.yekongle.shopmanager.common;

/**
 * @author Yekongle
 * @date 2020/10/25 15:36
 */
public interface BaseErrorInfoInterface {
    /** 错误码*/
    Integer getResultCode();

    /** 错误描述*/
    String getResultMsg();
}
