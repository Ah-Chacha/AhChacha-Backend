package AhChacha.Backend.exception;

import AhChacha.Backend.dto.response.status.ResponseStatus;
import AhChacha.Backend.exception.AhchachaException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends AhchachaException {

    private final ResponseStatus exceptionStatus;
    public NotFoundException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
        this.exceptionStatus = exceptionStatus;
    }
}
