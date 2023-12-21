package AhChacha.Backend.exception;

import AhChacha.Backend.dto.response.status.ResponseStatus;

public class BadRequestException extends AhchachaException {

    private final ResponseStatus exceptionStatus;
    public BadRequestException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
        this.exceptionStatus = exceptionStatus;
    }
}
