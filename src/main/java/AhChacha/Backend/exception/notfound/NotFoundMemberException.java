package AhChacha.Backend.exception.notfound;

import AhChacha.Backend.exception.AhchachaException;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

public class NotFoundMemberException extends AhchachaException {
    public NotFoundMemberException() {
        super(USER_NOT_FOUND);
    }
}
