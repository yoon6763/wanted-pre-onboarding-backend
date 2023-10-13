package preonboarding.wanted.backend.exception;

public class ApplyNotFoundException extends RuntimeException {
    public ApplyNotFoundException() {
        super("유효하지 않은 지원 정보 id 입니다.");
    }
}
