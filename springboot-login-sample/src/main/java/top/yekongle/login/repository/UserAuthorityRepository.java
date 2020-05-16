package top.yekongle.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import top.yekongle.login.entity.UserAuthority;

/** 
* @Description: 
* @Author: Yekongle 
* @Date: 2020年5月14日
*/
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
	List<UserAuthority> findByUsername(String username);
}
