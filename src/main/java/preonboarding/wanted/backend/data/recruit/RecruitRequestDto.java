package preonboarding.wanted.backend.data.recruit;

import lombok.Data;

@Data
public class RecruitRequestDto {
    private Long companyId;
    private String position;
    private Long compensation;
    private String content;
    private String tech;
}
