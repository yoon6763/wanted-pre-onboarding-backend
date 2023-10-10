package preonboarding.wanted.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.wanted.backend.data.company.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findById(Long id);
}
