package by.itacademy.config;

import by.itacademy.config.properties.GoogleDriveProperties;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class FileStorageConfig {

    private final GoogleDriveProperties properties;
    private final List<String> SCOPES =
            Collections.singletonList(DriveScopes.DRIVE_FILE);

    public FileStorageConfig(GoogleDriveProperties properties) {
        this.properties = properties;
    }

    @Bean
    public JsonFactory jsonFactory() {
        return GsonFactory.getDefaultInstance();
    }

    @Bean
    public Drive drive() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Drive.Builder(HTTP_TRANSPORT, jsonFactory(), getCredentials(HTTP_TRANSPORT))
                .setApplicationName(properties.getApplicationName())
                .build();
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        String credentialsFilePath = properties.getCredentialsFilePath();
        InputStream in = FileStorageConfig.class.getResourceAsStream(credentialsFilePath);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + credentialsFilePath);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(jsonFactory(), new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, jsonFactory(), clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(
                        new java.io.File(properties.getTokensDirectoryPath())))
                .setAccessType(properties.getAccessType())
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder()
                .setPort(properties.getPort())
                .setHost(properties.getHost())
                .build();
        return new AuthorizationCodeInstalledApp(flow, receiver)
                .authorize(properties.getUserId());
    }
}