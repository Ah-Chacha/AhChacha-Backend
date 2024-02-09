package AhChacha.Backend.exception;

import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;

public class UnauthorizedException extends AhchachaException {

    public UnauthorizedException(BaseExceptionResponseStatus responseStatus) {
        super(responseStatus);
    }
}
