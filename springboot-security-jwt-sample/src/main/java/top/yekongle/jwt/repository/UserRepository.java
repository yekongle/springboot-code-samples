package top.yekongle.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.yekongle.jwt.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
}