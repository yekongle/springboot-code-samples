package top.yekongle.restful.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * @Description: 持久化实体类
 * @Data: lombok 注解，自动生成 getter, setter方法，重写equals,hash,toString方法
 * @Entity: 表明该类是持久化实体类(映射到对应table)
 * @Id: 指定 table id
 * @GeneratedValue: 默认采用自增长策略
 * @Author: Yekongle
 * @Date: Apr 7, 2020
 */
@Data
@Entity
public class Employee {
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String role;

	public Employee() {
	}

	public Employee(String name, String role) {
		this.name = name;
		this.role = role;
	}
}
