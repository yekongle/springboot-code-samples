package top.yekongle.activate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import top.yekongle.activate.entity.VerificationToken;
import top.yekongle.activate.entity.User;
import top.yekongle.activate.entity.VerificationToken;;

/** 
* @Description: 
* @Author: Yekongle 
* @Date: 2020年6月6日
*/
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
	VerificationToken findByToken(String token);
	VerificationToken findByUser(User user);
}
