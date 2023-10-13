package preonboarding.wanted.backend.data.apply;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplyInfoDto {
    private Long userId;
    private Long recruitId;
}
