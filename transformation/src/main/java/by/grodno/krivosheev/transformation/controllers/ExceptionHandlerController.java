package by.grodno.krivosheev.transformation.controllers;

import by.grodno.krivosheev.transformation.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @Value("${spring.servlet.multipart.max-file-size:1GB}")
    private String maxFileSize;

    /**
     * This exception handler catches: <p>
     * - IllegalArgumentException if request has invalid param; <p>
     * - MaxUploadSizeExceededException if request has file more that maxFileSize; <p>
     * - PropertyReferenceException if request has invalid param SORT.
     *
     * @param e object catches exception.
     * @return custom {@link ErrorResponse} with code 400 and message about bad request.
     */
    @ExceptionHandler({IllegalArgumentException.class, MaxUploadSizeExceededException.class, PropertyReferenceException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @PostMapping(produces = "application/json")
    public ResponseEntity<ErrorResponse> handleBadRequest(Object e) {
        var msg = "Parameters are invalid...";

        if (e instanceof MaxUploadSizeExceededException) {
            msg = "Maximum upload size " + maxFileSize + "!";
        } else if (e instanceof IllegalArgumentException) {
            msg = ((IllegalArgumentException) e).getMessage();
        } else if (e instanceof PropertyReferenceException) {
            msg = ((PropertyReferenceException) e).getMessage();
        }
        log.warn(msg, (Exception) e);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, msg), HttpStatus.BAD_REQUEST);
    }

    /**
     * This exception handler catches all exceptions for which there are no handlers.
     *
     * @param e catches exception.
     * @return custom {@link ErrorResponse} with code 500 and default message.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @PostMapping(produces = "application/json")
    public ResponseEntity<ErrorResponse> handleInternal(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
