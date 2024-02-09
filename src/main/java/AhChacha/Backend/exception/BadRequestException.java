package AhChacha.Backend.exception;

import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;

public class BadRequestException extends AhchachaException {

    public BadRequestException(BaseExceptionResponseStatus responseStatus) {
        super(responseStatus);
    }
}
