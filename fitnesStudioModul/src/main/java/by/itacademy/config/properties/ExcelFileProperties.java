package by.itacademy.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "excel")
public class ExcelFileProperties {

    private String columnUuid;
    private String columnDtCreate;
    private String columnDtUpdate;
    private String columnMail;
    private String columnFio;
    private String columnRole;
    private String columnText;
    private String columnType;
    private String columnId;

    public String getColumnUuid() {
        return columnUuid;
    }

    public void setColumnUuid(String columnUuid) {
        this.columnUuid = columnUuid;
    }

    public String getColumnDtCreate() {
        return columnDtCreate;
    }

    public void setColumnDtCreate(String columnDtCreate) {
        this.columnDtCreate = columnDtCreate;
    }

    public String getColumnDtUpdate() {
        return columnDtUpdate;
    }

    public void setColumnDtUpdate(String columnDtUpdate) {
        this.columnDtUpdate = columnDtUpdate;
    }

    public String getColumnMail() {
        return columnMail;
    }

    public void setColumnMail(String columnMail) {
        this.columnMail = columnMail;
    }

    public String getColumnFio() {
        return columnFio;
    }

    public void setColumnFio(String columnFio) {
        this.columnFio = columnFio;
    }

    public String getColumnRole() {
        return columnRole;
    }

    public void setColumnRole(String columnRole) {
        this.columnRole = columnRole;
    }

    public String getColumnText() {
        return columnText;
    }

    public void setColumnText(String columnText) {
        this.columnText = columnText;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }
}