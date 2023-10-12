package preonboarding.wanted.backend.data.recruit;

import lombok.*;
import preonboarding.wanted.backend.data.company.Company;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit {

    @Id
    @GeneratedValue
    @Column(name = "recruit_id", nullable = false)
    private Long id;
    private String position;
    private Long compensation;
    private String content;
    private String tech;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public void setCompany(Company company) {
        this.company = company;
        company.getRecruits().add(this);
    }

    public static Recruit of(Company company, RecruitRequestDto requestDto) {
        Recruit recruit = Recruit.builder()
                .position(requestDto.getPosition())
                .compensation(requestDto.getCompensation())
                .content(requestDto.getContent())
                .tech(requestDto.getTech())
                .build();

        recruit.setCompany(company);
        return recruit;
    }

    public RecruitInfoDto toInfoDto() {
        return RecruitInfoDto.builder()
                .id(this.id)
                .companyName(this.company.getName())
                .companyCountry(this.company.getCountry())
                .companyLocation(this.company.getLocation())
                .position(this.position)
                .compensation(this.compensation)
                .tech(this.tech)
                .build();
    }
}