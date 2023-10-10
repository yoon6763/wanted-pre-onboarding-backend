package preonboarding.wanted.backend.data.company;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class CompanyInfoDto {
    private Long id;
    private String name;
    private String country;
    private String location;
}
