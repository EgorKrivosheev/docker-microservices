package by.grodno.krivosheev.transformation.response;

import by.grodno.krivosheev.transformation.dto.BatchInfoDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This is response contains {@code batchInfoDTO} extends {@link AbstractResponse}. <p>
 * Where: <p>
 * - batchInfoDTO = it is contains info about batches (see {@link BatchInfoDTO}).
 */
@Getter
public class BatchResponse extends AbstractResponse {
    private final BatchInfoDTO batchInfoDTO;

    /**
     * This constructor use nested constructor and set moreInfo.
     *
     * @param httpStatus   enum {@link HttpStatus}.
     * @param batchInfoDTO it is containing info about batches (see {@link BatchInfoDTO}).
     */
    public BatchResponse(HttpStatus httpStatus, BatchInfoDTO batchInfoDTO) {
        super(httpStatus);
        this.batchInfoDTO = batchInfoDTO;
    }
}
