package preonboarding.wanted.backend.data.recruit;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecruitUpdateDto {
    private String position;
    private Long compensation;
    private String content;
    private String tech;
}
