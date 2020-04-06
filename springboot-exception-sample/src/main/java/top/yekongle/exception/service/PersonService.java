package top.yekongle.exception.service;

import top.yekongle.exception.model.Person;

import java.util.Map;

/**
 * @Description 业务接口
 */
public interface PersonService {
	Map<String, Object> register(Person person);
}
