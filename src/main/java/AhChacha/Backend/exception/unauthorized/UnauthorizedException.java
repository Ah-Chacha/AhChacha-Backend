package AhChacha.Backend.exception.unauthorized;

import AhChacha.Backend.exception.AhchachaException;
import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;

public class UnauthorizedException extends AhchachaException {

    public UnauthorizedException(BaseExceptionResponseStatus responseStatus) {
        super(responseStatus);
    }
}
