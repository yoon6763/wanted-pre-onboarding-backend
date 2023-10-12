package preonboarding.wanted.backend.data.recruit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecruitInfoDto {
    private Long id;
    private String companyName;
    private String companyCountry;
    private String companyLocation;
    private String position;
    private Long compensation;
    private String tech;
}