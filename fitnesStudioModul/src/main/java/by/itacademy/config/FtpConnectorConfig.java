package by.itacademy.config;

import by.itacademy.config.properties.FtpProperties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

@Configuration
public class FtpConnectorConfig {

    private final FtpProperties properties;

    public FtpConnectorConfig(FtpProperties properties) {
        this.properties = properties;
    }

    @Bean
    public SessionFactory<FTPFile> ftpSessionFactory() {
        DefaultFtpSessionFactory sf = new DefaultFtpSessionFactory();
        sf.setHost(properties.getHost());
        sf.setPort(properties.getPort());
        sf.setUsername(properties.getUsername());
        sf.setPassword(properties.getPassword());
        sf.setClientMode(properties.getClientMode());
        sf.setFileType(FTP.BINARY_FILE_TYPE);
        sf.setBufferSize(properties.getBufferSize());
        return new CachingSessionFactory<>(sf);
    }
}