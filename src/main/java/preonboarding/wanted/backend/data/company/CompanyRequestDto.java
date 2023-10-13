package preonboarding.wanted.backend.data.company;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRequestDto {
    private String name;
    private String country;
    private String location;
}
