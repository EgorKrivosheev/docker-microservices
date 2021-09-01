package by.grodno.krivosheev.transformation.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is help get item of the items in xml file for {@link javax.xml.bind.JAXBContext}. <p>
 *
 * Where: <p>
 *  - id = tag id item; <p>
 *  - topic = tag topic item; <p>
 *  - content = tag content item.
 */
@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
public class Item {
    private String id;
    private String topic;
    private String content;
}
