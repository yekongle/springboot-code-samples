package top.yekongle.shopmanager.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.yekongle.shopmanager.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yekongle
 * @since 2020-10-24
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{usernmae}")
    User selectByUsername(@Param("usernmae") String usernmae);
}
