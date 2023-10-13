package preonboarding.wanted.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import preonboarding.wanted.backend.data.recruit.Recruit;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    List<Recruit> findAllByCompanyId(Long companyId);

    // 기술 및 회사 이름 검색
    @Query("select r from Recruit r where r.tech like %?1% or r.company.name like %?1%")
    List<Recruit> searchRecruit(String keyword);

}
