package preonboarding.wanted.backend.data.company;

import lombok.*;

@AllArgsConstructor
@Data
public class CompanyUpdateDto {
    private String name;
    private String country;
    private String location;
}
