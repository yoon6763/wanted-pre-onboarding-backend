package preonboarding.wanted.backend.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("유효하지 않은 유저 id 입니다.");
    }
}
