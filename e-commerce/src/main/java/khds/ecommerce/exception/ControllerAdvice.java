package khds.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotIncludedMapException(NoHandlerFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("잘못된 URL입니다. 다시 한번 확인해 주세요 "));
    }
}
