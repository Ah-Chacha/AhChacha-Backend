package AhChacha.Backend.exception;

import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;

public class NotFoundException extends AhchachaException {
    public NotFoundException(BaseExceptionResponseStatus responseStatus) {
        super(responseStatus);
    }
}
