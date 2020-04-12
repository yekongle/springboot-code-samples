package top.yekongle.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.yekongle.restful.entity.Employee;
import top.yekongle.restful.exception.EmployeeNotFoundException;
import top.yekongle.restful.repository.EmployeeRepository;


/**
 * @Description: 员工controller类, 
 * @Author: Yekongle
 * @Date: Apr 7, 2020
 */

// @RestController 注解会使每个方法返回值直接写入到response body中，而不是去渲染模板
@RestController
public class EmployeeController {
	@Autowired
	EmployeeRepository repository;

    /**
     * 查找所有员工
	 */
	@GetMapping("/employees")
	public ResponseEntity<?> all() {
		List<Employee> employeeList = repository.findAll();
		return ResponseEntity.ok(employeeList);
	}
	
    /**
     * 新建员工
	 */
	@PostMapping("/employees")
	public ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
		repository.save(newEmployee);
		return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
	}
	
    /**
     * 根据id查找员工
     * @PathVariable 将参数与请求路径模板绑定起来，{id}的值就是参数id的值
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> one(@PathVariable Long id) {
		// 根据id查找员工，找不到抛出一个自定义 exception
		Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		return ResponseEntity.ok(employee);
	}

	/**
     * 更新员工信息
     * @PathVariable 将参数与请求路径模板绑定起来，{id}的值就是参数id的值
	 */
	@PutMapping("/employees/{id}")
	public ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		// 根据id查找员工，找到就update信息再保存，找不到就直接新建一个员工
		repository.findById(id)
			.map(employee -> {
				employee.setName(newEmployee.getName());
				employee.setRole(newEmployee.getRole());
				return repository.save(employee);
			})
			.orElseGet(() -> {
				newEmployee.setId(id);
				return repository.save(newEmployee);
			});
		
		return ResponseEntity.noContent().build();
	}
	
	/**
     * 删除员工
     * @PathVariable 将参数与请求路径模板绑定起来，{id}的值就是参数id的值
	 */
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
