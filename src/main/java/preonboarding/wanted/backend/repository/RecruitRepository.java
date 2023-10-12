package preonboarding.wanted.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.wanted.backend.data.recruit.Recruit;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {

}
