package by.itacademy.service.impl;

import by.itacademy.config.properties.ExcelFileProperties;
import by.itacademy.repository.entity.AuditEntity;
import by.itacademy.service.api.IExcelFileWriter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExcelFileWriterService implements IExcelFileWriter {

    private final ExcelFileProperties properties;
    public final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ExcelFileWriterService(ExcelFileProperties properties) {
        this.properties = properties;
    }

    @Override
    public byte[] write(List<AuditEntity> audits) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet();
            createHeaderRow(sheet);
            int rowNum = 1;
            for (AuditEntity audit : audits) {
                Row currentRow = sheet.createRow(++rowNum);
                int columnNum = 0;
                currentRow.createCell(columnNum).setCellValue(audit.getUuid().toString());
                currentRow.createCell(++columnNum).setCellValue(format(audit.getDtCreate()));
                currentRow.createCell(++columnNum).setCellValue(audit.getUserUuid().toString());
                currentRow.createCell(++columnNum).setCellValue(audit.getMail());
                currentRow.createCell(++columnNum).setCellValue(audit.getFio());
                currentRow.createCell(++columnNum).setCellValue(audit.getRole().getRole().name());
                currentRow.createCell(++columnNum).setCellValue(audit.getText());
                currentRow.createCell(++columnNum).setCellValue(audit.getType().getType().name());
                currentRow.createCell(++columnNum).setCellValue(audit.getType().getId());
            }
            workbook.write(baos);
            return baos.toByteArray();
        }
    }

    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        int columnNum = 0;
        headerRow.createCell(columnNum).setCellValue(properties.getColumnUuid());
        headerRow.createCell(++columnNum).setCellValue(properties.getColumnDtCreate());
        headerRow.createCell(++columnNum).setCellValue(properties.getColumnDtUpdate());
        headerRow.createCell(++columnNum).setCellValue(properties.getColumnMail());
        headerRow.createCell(++columnNum).setCellValue(properties.getColumnFio());
        headerRow.createCell(++columnNum).setCellValue(properties.getColumnRole());
        headerRow.createCell(++columnNum).setCellValue(properties.getColumnText());
        headerRow.createCell(++columnNum).setCellValue(properties.getColumnType());
        headerRow.createCell(++columnNum).setCellValue(properties.getColumnId());
    }

    private String format(LocalDateTime dateTime) {
        return FORMATTER.format(dateTime);
    }
}