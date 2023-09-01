package by.grodno.krivosheev.transformation.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This is error response contains {@code moreInfo} extends {@link AbstractResponse}. <p>
 * Where: <p>
 * - moreInfo = message about error.
 */
@Getter
public class ErrorResponse extends AbstractResponse {
    private final String moreInfo;

    /**
     * This constructor use nested constructor and set default value for {@code moreInfo}.
     *
     * @param httpStatus enum {@link HttpStatus}.
     */
    public ErrorResponse(HttpStatus httpStatus) {
        super(httpStatus);
        this.moreInfo = "Sorry something went wrong...";
    }

    /**
     * This constructor use nested constructor and set moreInfo.
     *
     * @param httpStatus enum {@link HttpStatus};
     * @param moreInfo   it is a message about error.
     */
    public ErrorResponse(HttpStatus httpStatus, String moreInfo) {
        super(httpStatus);
        this.moreInfo = moreInfo;
    }
}
