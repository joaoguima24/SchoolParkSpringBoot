package academy.mindswap.schoolpark.schoolpark.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger("Not Found");
    @ExceptionHandler (value = {NotFoundException.class})
    public ResponseEntity<String> handleNotFoundException (Exception exception, HttpServletRequest request){
        LOGGER.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }
}
