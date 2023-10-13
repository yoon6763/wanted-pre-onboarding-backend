package preonboarding.wanted.backend.exception;

public class DuplicateApplyException extends RuntimeException {
    public DuplicateApplyException() {
        super("이미 지원한 공고입니다.");
    }
}
