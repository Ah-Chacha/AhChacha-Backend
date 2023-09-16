package AhChacha.Backend.dto.response.status;

import org.springframework.http.HttpStatus;

public interface ResponseStatus {
    int getCode();
    HttpStatus getHttpStatus();
    String getMessage();
}
