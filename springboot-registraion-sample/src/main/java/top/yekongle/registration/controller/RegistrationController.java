package top.yekongle.registration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import top.yekongle.registration.dto.UserDTO;
import top.yekongle.registration.entity.User;
import top.yekongle.registration.service.UserService;
import top.yekongle.registration.util.GenericResponse;


@Slf4j
@RequestMapping("/user")
@Controller
public class RegistrationController {
    @Autowired UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    @ResponseBody
    public GenericResponse registerUserAccount(@Valid UserDTO userDTO) {
        User registered = userService.registerNewUserAccount(userDTO);

        return new GenericResponse("success");
    }
}
