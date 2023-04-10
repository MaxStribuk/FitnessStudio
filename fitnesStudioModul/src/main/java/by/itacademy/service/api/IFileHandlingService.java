package by.itacademy.service.api;

import org.springframework.core.io.Resource;

import java.io.IOException;

public interface IFileHandlingService {

    void upload(String fileName, byte[] bytes) throws IOException;

    Resource download(String fileName) throws IOException;
}