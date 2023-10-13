package preonboarding.wanted.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.wanted.backend.data.apply.Apply;
import preonboarding.wanted.backend.data.recruit.Recruit;
import preonboarding.wanted.backend.data.user.User;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    List<Apply> findByUser(User user);

    Optional<Apply> findByUserAndRecruit(User user, Recruit recruit);
}
