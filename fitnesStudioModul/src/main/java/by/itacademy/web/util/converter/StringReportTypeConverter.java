package by.itacademy.web.util.converter;

import by.itacademy.core.enums.ReportType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringReportTypeConverter implements Converter<String, ReportType> {

    @Override
    public ReportType convert(String reportType) {
        try {
            return ReportType.valueOf(reportType);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}