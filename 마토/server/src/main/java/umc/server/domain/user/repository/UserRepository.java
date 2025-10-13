package umc.server.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
}
