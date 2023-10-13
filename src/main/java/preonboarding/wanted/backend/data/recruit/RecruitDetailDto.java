package preonboarding.wanted.backend.data.recruit;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecruitDetailDto {
    private Long id;
    private String companyName;
    private String companyCountry;
    private String companyLocation;
    private String position;
    private Long compensation;
    private String tech;
    private String content;
    private List<Long> otherRecruitIds;
}