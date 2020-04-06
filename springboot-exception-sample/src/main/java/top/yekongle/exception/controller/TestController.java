package top.yekongle.exception.controller;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yekongle.exception.dto.TestDto;
import top.yekongle.exception.model.Person;
import top.yekongle.exception.response.ApiResponse;
import top.yekongle.exception.service.PersonService;

/**
 * @Description 测试异常控制类
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private PersonService personService;

    /**
     * 测试捕捉参数验证 exception
     * */
	@PostMapping("/validate")
	public ApiResponse testValidate(@RequestBody @Validated TestDto testDto) {
		return ApiResponse.success(testDto);
	}

    /**
     * 测试捕捉业务验证 exception
     * */
    @PostMapping("/business")
    public ApiResponse init(@RequestBody Person person) {
        Map resultMap = personService.register(person);
        return ApiResponse.success(resultMap);
    }

    /**
     * 测试捕捉系统 exception
     * */
    @PostMapping("/error")
    public ApiResponse testValidate() {
        TestDto testDto = null;
        log.info("Result:" + testDto.toString());
        return ApiResponse.success(null);
    }
}
