package preonboarding.wanted.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.wanted.backend.data.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
