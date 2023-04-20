package by.itacademy.service.api;

import by.itacademy.core.dto.transfer.AuditDto;

import java.io.IOException;
import java.util.List;

public interface IExcelFileWriter {

    byte[] write(List<AuditDto> audits) throws IOException;
}