package by.grodno.krivosheev.transformation.services;

import by.grodno.krivosheev.transformation.entities.ItemEntity;
import by.grodno.krivosheev.transformation.pojo.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransformationServiceImpl implements TransformationService {
    private static final int MB = 1048576;

    private final ItemService itemService;

    @Value("${size-batch-save-database:100}")
    private int size;

    @Override
    @Async("listenerTaskExecutor")
    public void transformation(File file) throws IOException {
        var runtime = Runtime.getRuntime();
        log.debug(getMemory(runtime));
        // unchecked because file's name NUMBER.zip (see BatchService#save)
        var idBatch = Long.parseLong(file.getName().substring(0, file.getName().length() - 4));
        List<ItemEntity> arrayList = new ArrayList<>(size);

        try (var zip = new ZipFile(file)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            var jaxbContext = JAXBContext.newInstance(Item.class);
            var unmarshaller = jaxbContext.createUnmarshaller();
            var xmlInputFactory = XMLInputFactory.newFactory();
            xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);

            log.debug(getMemory(runtime));

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                var xmlStreamReader = xmlInputFactory.createXMLStreamReader(zip.getInputStream(entry));
                // Miss <items>
                xmlStreamReader.nextTag();
                xmlStreamReader.nextTag();

                while (xmlStreamReader.hasNext()) {
                    arrayList.add(new ItemEntity((Item) unmarshaller.unmarshal(xmlStreamReader), idBatch));

                    if (arrayList.size() == size) {
                        itemService.saveAllAndPushElasticsearch(arrayList);
                        arrayList.clear();
                    }

                    if (xmlStreamReader.nextTag() != XMLStreamConstants.START_ELEMENT) {
                        break; // Miss not <item>
                    }
                    log.debug(getMemory(runtime));
                }
                if (!arrayList.isEmpty()) {
                    itemService.saveAllAndPushElasticsearch(arrayList);
                    arrayList.clear();
                }
            }
        } catch (IOException | XMLStreamException | JAXBException e) {
            log.error(e.getMessage(), e);
        }
    }

    private String getMemory(Runtime runtime) {
        return "Total: " + runtime.totalMemory() / MB + " free: " + runtime.freeMemory() / MB +
                " used: " + (runtime.totalMemory() - runtime.freeMemory()) / MB;
    }
}
