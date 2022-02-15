package kr.co.easystock.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User>
{
    Optional<User> findByName(String name);
    Optional<User> findByIdAndPassword(String id, String password);
    Optional<User> findByIdAndDeletedDateNull(String id);
}
