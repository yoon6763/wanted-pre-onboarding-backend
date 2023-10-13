package preonboarding.wanted.backend.exception;

public class RecruitNotFoundException extends RuntimeException {
    public RecruitNotFoundException() {
        super("유효하지 않은 공고 id 입니다.");
    }
}
