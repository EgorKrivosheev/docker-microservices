package by.grodno.krivosheev.transformation.response;

import by.grodno.krivosheev.transformation.elastic.pojo.ItemDocument;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * This is response contains {@code count, list} extends {@link AbstractResponse}. <p>
 * <p>
 * Where: <p>
 * - count = size list; <p>
 * - list = it is containing items in {@link ItemDocument}.
 */
@Getter
public class ListDocumentResponse extends AbstractResponse {
    private final int count;
    private final List<ItemDocument> list;

    /**
     * This construct use nested constructor and set list with items of the elasticsearch.
     *
     * @param httpStatus enum {@link HttpStatus};
     * @param list       it is containing items in {@link ItemDocument}.
     */
    public ListDocumentResponse(HttpStatus httpStatus, List<ItemDocument> list) {
        super(httpStatus);
        this.count = list.size();
        this.list = list;
    }
}
