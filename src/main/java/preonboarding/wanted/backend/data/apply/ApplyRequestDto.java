package preonboarding.wanted.backend.data.apply;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyRequestDto {
    private Long userId;
    private Long recruitId;
}
