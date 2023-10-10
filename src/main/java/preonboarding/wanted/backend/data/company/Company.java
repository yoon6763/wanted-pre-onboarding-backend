package preonboarding.wanted.backend.data.company;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "company_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    private String country;
    private String location;

    public static Company of(CompanyRequestDto requestDto) {
        return Company.builder()
                .name(requestDto.getName())
                .country(requestDto.getCountry())
                .location(requestDto.getLocation())
                .build();
    }

    public void update(CompanyUpdateDto editDto) {
        this.name = editDto.getName();
        this.country = editDto.getCountry();
        this.location = editDto.getLocation();
    }

    public CompanyInfoDto toInfoDto() {
        return CompanyInfoDto.builder()
                .id(this.id)
                .name(this.name)
                .country(this.country)
                .location(this.location)
                .build();
    }
}