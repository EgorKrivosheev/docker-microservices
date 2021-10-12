package by.grodno.krivosheev.transformation.response;

import lombok.Getter;

import org.springframework.http.HttpStatus;

/**
 * This is abstract response contains {@code status} and {@code message}. <p>
 *
 * Where: <p>
 *  - status = number HTTP Status; <p>
 *  - message = name HTTP Status.
 */
@Getter
public abstract class AbstractResponse {
    private final int status;
    private final String message;

    /**
     * This constructor gets {@code value, reasonPhrase} of HttpStatus.
     * @param httpStatus enum {@link HttpStatus}.
     */
    protected AbstractResponse(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }
}
