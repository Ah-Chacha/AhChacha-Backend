package AhChacha.Backend.exception.status;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BaseExceptionResponseStatus implements ResponseStatus{

    /*
     * code 1000번대 : 성공
     */
    SUCCESS(1000, HttpStatus.OK, "요청에 성공하였습니다."),

    /*
     * code 2000번대 : Server, DataBase 오류 (INTERENAL_SERVER_ERROR)
     */
    SERVER_ERROR(2000, HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 오류가 발생하였습니다."),
    DATABASE_ERROR(2001, HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스에서 오류가 발생하였습니다."),
    SQL_GRAMMATICAL_ERROR(2002, HttpStatus.INTERNAL_SERVER_ERROR, "SQL에 오류가 있습니다."),

    /*
     * code 3000번대 : Request 오류 (BAD_REQUEST)
     */
    BAD_REQUEST(3000, HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다."),
    BAD_URL(3001, HttpStatus.NOT_FOUND, "유효하지 않은 URL입니다."),
    BAD_HTTPMETHOD(3002, HttpStatus.METHOD_NOT_ALLOWED, "해당 URL에서 지원하지 않는 HTTP Method 입니다."),

    /*
     * code 4000번대 : Authorization 오류
     */
    JWT_ERROR(4000, HttpStatus.UNAUTHORIZED, "JWT에서 오류가 발생하였습니다."),
    TOKEN_NOTFOUND(4001, HttpStatus.BAD_REQUEST,"토큰이 HTTP Header에 없습니다."),
    EXPIRED_TOKEN(4002, HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(4003, HttpStatus.BAD_REQUEST, "지원하지 않는 토큰 형식입니다."),
    MALFORMED_TOKEN(4004, HttpStatus.UNAUTHORIZED, "토큰이 올바르게 구성되지 않았습니다."),
    INVALID_TOKEN(4005, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_MISMATCH(4006, HttpStatus.UNAUTHORIZED, "로그인 정보가 토큰 정보와 일치하지 않습니다."),
    /*
     * code 5000번대 : User 오류 (회원가입, 로그인 시 잘못된 요청)
     */
    DUPLICATE_EMAIL(5000, HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    DUPLICATE_NICKNAME(5001, HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    PASSWORD_MISMATCH(5002, HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    EMAIL_NOT_FOUND(5003, HttpStatus.BAD_REQUEST, "존재하지 않는 이메일입니다."),
    USER_NOT_FOUND(5004, HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다.");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
