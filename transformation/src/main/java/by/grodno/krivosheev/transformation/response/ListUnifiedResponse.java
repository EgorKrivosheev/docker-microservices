package by.grodno.krivosheev.transformation.response;

import by.grodno.krivosheev.transformation.dto.UnifiedFormatDTO;

import lombok.Getter;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * This is response contains {@code count, list} extends {@link AbstractResponse}. <p>
 *
 * Where: <p>
 *  - count = size list; <p>
 *  - list = it is contains items in {@link UnifiedFormatDTO}.
 */
@Getter
public class ListUnifiedResponse extends AbstractResponse {
    private final int count;
    private final List<UnifiedFormatDTO> list;

    /**
     * This construct use nested constructor and set list with items in unified format.
     * @param httpStatus enum {@link HttpStatus};
     * @param list it is contains items in {@link UnifiedFormatDTO}.
     */
    public ListUnifiedResponse(HttpStatus httpStatus, List<UnifiedFormatDTO> list) {
        super(httpStatus);
        this.count = list.size();
        this.list = list;
    }
}
