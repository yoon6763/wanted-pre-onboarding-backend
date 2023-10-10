package preonboarding.wanted.backend.data.company;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CompanyRequestDto {
    private String name;
    private String country;
    private String location;
}
