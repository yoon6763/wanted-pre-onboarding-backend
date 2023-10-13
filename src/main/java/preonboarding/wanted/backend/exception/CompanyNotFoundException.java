package preonboarding.wanted.backend.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("유효하지 않은 회사 id 입니다.");
    }
}
