package by.itacademy.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "excel")
public class ExcelProperties {

    private String exampleFileName;
    private String sheetName;

    public String getExampleFileName() {
        return exampleFileName;
    }

    public void setExampleFileName(String exampleFileName) {
        this.exampleFileName = exampleFileName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}