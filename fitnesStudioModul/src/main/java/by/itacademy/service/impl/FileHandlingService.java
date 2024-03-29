package by.itacademy.service.impl;

import by.itacademy.config.properties.GoogleDriveProperties;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.service.api.IFileHandlingService;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        file.setParents(Collections.singletonList(this.properties.getFolderId()));
        file.setName(fileName + this.properties.getFileExtension());
        String contentType = this.properties.getContentType();
        try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
            InputStreamContent inputStreamContent = new InputStreamContent(contentType, inputStream);
            this.drive.files()
                    .create(file, inputStreamContent)
                    .execute();
        }
    }

    @Override
    public Resource download(String fileName) throws IOException {
        String query = this.properties.getQueryForFileFolder();
        String fullFileName = fileName + this.properties.getFileExtension();
        String id = this.drive.files()
                .list()
                .setQ(query)
                .execute()
                .getFiles()
                .stream()
                .filter(file -> file.getName().equals(fullFileName))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("file not found"))
                .getId();
        return new InputStreamResource(
                this.drive.files()
                        .get(id)
                        .executeMediaAsInputStream());
    }
}