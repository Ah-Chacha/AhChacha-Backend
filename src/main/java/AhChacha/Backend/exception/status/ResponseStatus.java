package AhChacha.Backend.exception.status;

import org.springframework.http.HttpStatus;

public interface ResponseStatus {
    int getCode();
    HttpStatus getHttpStatus();
    String getMessage();
}
