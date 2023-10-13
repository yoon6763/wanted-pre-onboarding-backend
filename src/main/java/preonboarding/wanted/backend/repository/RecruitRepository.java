package preonboarding.wanted.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.wanted.backend.data.recruit.Recruit;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    List<Recruit> findAllByCompanyId(Long companyId);

}
