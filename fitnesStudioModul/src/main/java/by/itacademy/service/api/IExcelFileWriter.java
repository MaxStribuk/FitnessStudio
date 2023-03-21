package by.itacademy.service.api;

import by.itacademy.repository.entity.AuditEntity;

import java.io.IOException;
import java.util.List;

public interface IExcelFileWriter {

    byte[] write(List<AuditEntity> audits) throws IOException;
}