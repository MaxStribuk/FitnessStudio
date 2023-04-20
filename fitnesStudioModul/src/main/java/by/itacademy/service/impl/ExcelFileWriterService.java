package by.itacademy.service.impl;

import by.itacademy.config.properties.ExcelProperties;
import by.itacademy.core.dto.transfer.AuditDto;
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

    private final ExcelProperties properties;
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ExcelFileWriterService(ExcelProperties properties) {
        this.properties = properties;
    }

    @Override
    public byte[] write(List<AuditDto> audits) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet();
            Row headerRow = sheet.createRow(0);
            createHeadersRow(headerRow);
            int rowNum = 1;
            for (AuditDto audit : audits) {
                Row currentRow = sheet.createRow(++rowNum);
                fillRow(audit, currentRow);
            }
            setAutosize(sheet);
            workbook.write(baos);
            return baos.toByteArray();
        }
    }

    private void fillRow(AuditDto audit, Row currentRow) {
        int columnNum = 0;
        currentRow.createCell(columnNum)
                .setCellValue(audit.getUuid().toString());
        currentRow.createCell(++columnNum)
                .setCellValue(format(audit.getDtCreate()));
        currentRow.createCell(++columnNum)
                .setCellValue(audit.getUserUuid().toString());
        currentRow.createCell(++columnNum)
                .setCellValue(audit.getMail());
        currentRow.createCell(++columnNum)
                .setCellValue(audit.getFio());
        currentRow.createCell(++columnNum)
                .setCellValue(audit.getRole().name());
        currentRow.createCell(++columnNum)
                .setCellValue(audit.getText());
        currentRow.createCell(++columnNum)
                .setCellValue(audit.getType().name());
        currentRow.createCell(++columnNum)
                .setCellValue(audit.getType().ordinal() + 1);
    }

    private void createHeadersRow(Row headerRow) {
        int columnNum = 0;
        headerRow.createCell(columnNum)
                .setCellValue(properties.getUuid());
        headerRow.createCell(++columnNum)
                .setCellValue(properties.getDtCreate());
        headerRow.createCell(++columnNum)
                .setCellValue(properties.getUserUuid());
        headerRow.createCell(++columnNum)
                .setCellValue(properties.getMail());
        headerRow.createCell(++columnNum)
                .setCellValue(properties.getFio());
        headerRow.createCell(++columnNum)
                .setCellValue(properties.getRole());
        headerRow.createCell(++columnNum)
                .setCellValue(properties.getText());
        headerRow.createCell(++columnNum)
                .setCellValue(properties.getType());
        headerRow.createCell(++columnNum)
                .setCellValue(properties.getTypeId());
    }

    private String format(LocalDateTime dateTime) {
        return FORMATTER.format(dateTime);
    }

    private void setAutosize(Sheet sheet) {
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }
    }
}