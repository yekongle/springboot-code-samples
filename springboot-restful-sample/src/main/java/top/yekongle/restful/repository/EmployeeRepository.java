package top.yekongle.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.yekongle.restful.entity.Employee;


/** 
* @Description: 数据操作接口，继承JpaRepository
* @Author: Yekongle 
* @Date: Apr 7, 2020
*/
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
