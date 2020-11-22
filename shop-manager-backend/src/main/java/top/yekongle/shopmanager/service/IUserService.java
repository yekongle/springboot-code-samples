package top.yekongle.shopmanager.service;

import top.yekongle.shopmanager.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yekongle
 * @since 2020-10-24
 */
public interface IUserService extends IService<User> {
    String authenUser(User user);

    String logout(User user);
}
