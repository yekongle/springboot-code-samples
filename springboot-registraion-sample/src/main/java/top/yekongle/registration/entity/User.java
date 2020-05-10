package top.yekongle.registration.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @Description: 用户实体
* @Author: Yekongle 
* @Date: 2020年5月5日
*/

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String password;
	
	@Transient
	private List<String> roles;
}
