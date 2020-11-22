package top.yekongle.shopmanager.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Yekongle
 * @date 2020/10/27 23:15
 */
@Data
public class PageVO<T> {
    private Long current;
    private Integer pageSize;
    private Long total;
    private List<T> list;
}
