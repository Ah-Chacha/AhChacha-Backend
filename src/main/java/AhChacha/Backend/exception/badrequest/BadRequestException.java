package AhChacha.Backend.exception.badrequest;

import AhChacha.Backend.exception.AhchachaException;
import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;

public class BadRequestException extends AhchachaException {

    public BadRequestException(BaseExceptionResponseStatus responseStatus) {
        super(responseStatus);
    }
}
