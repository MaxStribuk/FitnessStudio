package by.itacademy.service.api;

import java.io.IOException;

public interface IFileHandlingService {

    void upload(String fileName, byte[] bytes) throws IOException;
}