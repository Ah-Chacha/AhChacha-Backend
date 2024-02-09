package AhChacha.Backend.exception;

import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AhchachaException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
    private final int code;

    public AhchachaException(BaseExceptionResponseStatus responseStatus) {
        this.httpStatus = responseStatus.getHttpStatus();
        this.message = responseStatus.getMessage();
        this.code = responseStatus.getCode();
    }
}
