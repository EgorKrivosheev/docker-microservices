package by.grodno.krivosheev.transformation.response;

import by.grodno.krivosheev.transformation.dto.BatchInfoDTO;

import lombok.Getter;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * This is response contains {@code count, list} extends {@link AbstractResponse}. <p>
 *
 * Where: <p>
 *  - count = size list; <p>
 *  - list = it is contains info about batches (see {@link BatchInfoDTO}).
 */
@Getter
public class ListBatchResponse extends AbstractResponse {
    private final int count;
    private final List<BatchInfoDTO> list;

    /**
     * This construct use nested constructor and set list with info about batches.
     * @param httpStatus enum {@link HttpStatus};
     * @param list it is info about batches (see {@link BatchInfoDTO}).
     */
    public ListBatchResponse(HttpStatus httpStatus, List<BatchInfoDTO> list) {
        super(httpStatus);
        this.count = list.size();
        this.list = list;
    }
}
