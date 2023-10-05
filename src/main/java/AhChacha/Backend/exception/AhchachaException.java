package AhChacha.Backend.exception;

import AhChacha.Backend.dto.response.status.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class AhchachaException extends RuntimeException{
    private ResponseStatus exceptionStatus;

    public AhchachaException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}
