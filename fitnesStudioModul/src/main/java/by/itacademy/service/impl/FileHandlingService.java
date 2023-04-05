package by.itacademy.service.impl;

import by.itacademy.config.properties.GoogleDriveProperties;
import by.itacademy.service.api.IFileHandlingService;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;

@Service
public class FileHandlingService implements IFileHandlingService {

    private final GoogleDriveProperties properties;
    private final Drive drive;

    public FileHandlingService(GoogleDriveProperties properties, Drive drive) {
        this.properties = properties;
        this.drive = drive;
    }

    @Override
    public void upload(String fileName, byte[] bytes) throws IOException {
        File file = new File();
        file.setParents(Collections.singletonList(properties.getFolderId()));
        file.setName(fileName + properties.getFileExtension());
        drive.files()
                .create(file, new InputStreamContent(
                        properties.getContentType(),
                        new ByteArrayInputStream(bytes))
                )
                .execute();
    }
}