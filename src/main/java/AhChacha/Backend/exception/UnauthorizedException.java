package AhChacha.Backend.exception;

import AhChacha.Backend.dto.response.status.ResponseStatus;

public class UnauthorizedException extends AhchachaException {

    private final ResponseStatus exceptionStatus;
    public UnauthorizedException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
        this.exceptionStatus = exceptionStatus;
    }
}
