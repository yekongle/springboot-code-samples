package top.yekongle.login.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @Description: 
* @Author: Yekongle 
* @Date: 2020年5月14日
*/

@Entity
@Data
@NoArgsConstructor
public class UserAuthority {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String role;
}
