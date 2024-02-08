package AhChacha.Backend.dto.response;

import AhChacha.Backend.exception.status.ResponseStatus;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
public class BaseErrorResponse implements ResponseStatus {
    private int code;
    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime timestamp;

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

    public BaseErrorResponse(ResponseStatus status) {
        this.code = status.getCode();
        this.httpStatus = status.getHttpStatus();
        this.message = status.getMessage();
        this.timestamp = LocalDateTime.now();
    }
}
