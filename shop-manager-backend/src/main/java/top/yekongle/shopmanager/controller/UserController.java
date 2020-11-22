package top.yekongle.shopmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.yekongle.shopmanager.common.CommonResult;
import top.yekongle.shopmanager.entity.User;
import top.yekongle.shopmanager.service.IUserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yekongle
 * @since 2020-10-24
 */
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody User user) {
        String token = userService.authenUser(user);
        return CommonResult.success(token);
    }

//    @PostMapping("/logout")
//    public CommonResult logout(@RequestBody User user) {
//        String token = userService.authenUser(user);
//        return CommonResult.success(token);
//    }

}

