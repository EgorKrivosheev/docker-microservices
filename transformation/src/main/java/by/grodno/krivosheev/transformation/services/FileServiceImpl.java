package by.grodno.krivosheev.transformation.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic-name:transformation}")
    private String topicName;

    @Override
    public File uploadAndSendKafka(MultipartFile file, String name) throws IOException {
        byte[] bytes = file.getBytes();
        try (var stream = new FileOutputStream(name)) {
            stream.write(bytes);
            stream.flush();
        }
        kafkaTemplate.send(topicName, name);
        return new File(name);
    }
}
