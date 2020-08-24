package top.yekongle.activate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import top.yekongle.activate.entity.UserAuthority;

/** 
* @Description: 
* @Author: Yekongle 
* @Date: 2020年5月14日
*/
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
	List<UserAuthority> findByUsername(String username);
}
