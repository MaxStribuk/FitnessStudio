package by.itacademy.service.impl;

import by.itacademy.config.properties.ExcelProperties;
import by.itacademy.core.Constants;
import by.itacademy.core.dto.transfer.AuditDto;
import by.itacademy.service.api.IExcelFileWriter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExcelFileWriterService implements IExcelFileWriter {

    private final ExcelProperties properties;
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);

    public ExcelFileWriterService(ExcelProperties properties) {
        this.properties = properties;
    }

    @Override
    public byte[] write(List<AuditDto> audits) throws IOException {
        try (InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(this.properties.getExampleFileName());
             Workbook workbook = new XSSFWorkbook(inputStream);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.getSheet(this.properties.getSheetName());
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
                .setCellValue(FORMATTER.format(audit.getDtCreate()));
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

    private void setAutosize(Sheet sheet) {
        short lastCellNum = sheet
                .getRow(0)
                .getLastCellNum();
        for (short i = 0; i < lastCellNum; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}