package top.yekongle.exception.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import top.yekongle.exception.exception.RestInputException;
import top.yekongle.exception.model.Person;
import top.yekongle.exception.service.PersonService;

/**
 * @Description 业务实现类
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public Map<String, Object> register(Person person) {
        Map<String, Object> resultMap = null;
        if (person.getAge().intValue() < 18) {
            throw new RestInputException("未成年人不能注册哦！");
        }

        resultMap = new HashMap<>();
        resultMap.put("ID", 666);
        return resultMap;
    }
}
