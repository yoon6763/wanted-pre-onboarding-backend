package preonboarding.wanted.backend.data.recruit;

import lombok.*;
import preonboarding.wanted.backend.data.apply.Apply;
import preonboarding.wanted.backend.data.company.Company;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id", nullable = false)
    private Long id;
    private String position;
    private Long compensation;
    private String content;
    private String tech;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.ALL)
    private final List<Apply> applies = new ArrayList<>();

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

    public RecruitDetailDto toDetailDto(List<Long> otherRecruitIds) {
        return RecruitDetailDto.builder()
                .id(this.id)
                .companyName(this.company.getName())
                .companyCountry(this.company.getCountry())
                .companyLocation(this.company.getLocation())
                .position(this.position)
                .compensation(this.compensation)
                .tech(this.tech)
                .content(this.content)
                .otherRecruitIds(otherRecruitIds)
                .build();
    }

    public void update(RecruitUpdateDto recruitUpdateDto) {
        this.position = recruitUpdateDto.getPosition();
        this.compensation = recruitUpdateDto.getCompensation();
        this.content = recruitUpdateDto.getContent();
        this.tech = recruitUpdateDto.getTech();
    }
}