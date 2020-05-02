package top.yekongle.restclient.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import top.yekongle.restclient.entity.Employee;

@Slf4j
@Controller
public class EmployeeController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 通过 RestTemplate 获取员工列表
	 * */
	@GetMapping("/employees")
	public String employees(Model model) {
		log.info("Get mapping");
		final String uri = "http://localhost:8080/employees";
		Employee[] employees = restTemplate.getForObject(uri, Employee[].class);
		List<Employee> employeeList = Arrays.asList(employees);
		log.info("employeeList:{}", employeeList.toString());
		
		model.addAttribute("title", "员工列表");
		model.addAttribute("employees", employeeList);
		model.addAttribute("addEmployee", "新增员工");
		
		return "index";
	}

	/**
	 * 通过 RestTemplate 删除员工信息
	 * */
	@RequestMapping(value = "/employees/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		final String uri = "http://localhost:8080/employees/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", String.valueOf(id));
		restTemplate.delete(uri, params);
		return "redirect:/employees";
	}

	/**
	 * 通过 RestTemplate 新建员工信息
	 * */
	@PostMapping(value = "/employees")
	public  ResponseEntity<?>  newEmployee(@RequestBody Employee newEmployee) {
		log.info("Post mapping");
		log.info("New employee:{}", newEmployee.toString());
		final String uri = "http://localhost:8080/employees";
		restTemplate.postForObject(uri, newEmployee, Employee.class);
		
		return ResponseEntity.ok().build();
	}

	/**
	 * 通过 RestTemplate 更新员工信息
	 * */
	@PutMapping(value = "/employees/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee updatedEmployee, @PathVariable Long id) {
		log.info("Put mapping");
		final String uri = "http://localhost:8080/employees/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", String.valueOf(id));
		restTemplate.put(uri, updatedEmployee, params);
		
		return ResponseEntity.ok().build();
	}
	
}
