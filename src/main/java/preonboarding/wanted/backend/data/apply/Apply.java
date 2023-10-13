package preonboarding.wanted.backend.data.apply;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.wanted.backend.data.company.Company;
import preonboarding.wanted.backend.data.recruit.Recruit;
import preonboarding.wanted.backend.data.user.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Recruit recruit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Apply(Recruit recruit, User user) {
        this.recruit = recruit;
        this.user = user;
    }

    public static Apply apply(Recruit recruit, User user) {
        Apply apply = Apply.builder()
                .recruit(recruit)
                .user(user)
                .build();

        recruit.getApplies().add(apply);
        user.getApplies().add(apply);

        return apply;
    }

    public ApplyInfoDto toInfoDto() {
        return ApplyInfoDto.builder()
                .userId(this.user.getId())
                .recruitId(this.recruit.getId())
                .build();
    }
}
