package AhChacha.Backend.controller;

import AhChacha.Backend.dto.BaseErrorResponse;
import AhChacha.Backend.exception.AhchachaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.DAY_EXCEPTION;
import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.VALID_EXCEPTION;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = AhchachaException.class)
    public ResponseEntity<BaseErrorResponse> processValidationError(AhchachaException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new BaseErrorResponse(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<BaseErrorResponse> processDateTimeError(DateTimeException ex) {
        return ResponseEntity.badRequest()
                .body(new BaseErrorResponse(DAY_EXCEPTION.getCode(), DAY_EXCEPTION.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorResponse> processValidateError(MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append("[" + fieldError.getField());
            sb.append("]은(는) " + fieldError.getDefaultMessage());
            sb.append("  입력된 값 : " + fieldError.getRejectedValue() + "\n");
        }
        String message = sb.toString();

        return ResponseEntity.badRequest()
                .body(new BaseErrorResponse(VALID_EXCEPTION.getCode(), VALID_EXCEPTION.getMessage() + message));
    }


}
