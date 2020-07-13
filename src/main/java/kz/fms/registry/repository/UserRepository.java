package kz.fms.registry.repository;

import kz.fms.registry.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author baur
 * @date on 01.07.2020
 */


 public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
