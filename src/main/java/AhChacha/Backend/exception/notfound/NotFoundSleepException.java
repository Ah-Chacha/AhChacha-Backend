package AhChacha.Backend.exception.notfound;

import AhChacha.Backend.exception.AhchachaException;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.SLEEP_NOT_FOUND;

public class NotFoundSleepException extends AhchachaException {
    public NotFoundSleepException() {
        super(SLEEP_NOT_FOUND);
    }
}
