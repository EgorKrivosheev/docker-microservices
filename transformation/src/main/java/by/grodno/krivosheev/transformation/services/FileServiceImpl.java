package by.grodno.krivosheev.transformation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Value("${topic-name:transformation}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public File uploadAndSendKafka(MultipartFile file, String name) throws IOException {
        byte[] bytes = file.getBytes();
        try(var stream = new FileOutputStream(name)) {
            stream.write(bytes);
            stream.flush();
        }
        kafkaTemplate.send(topicName, name);
        return new File(name);
    }
}
