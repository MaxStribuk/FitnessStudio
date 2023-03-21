package by.itacademy.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenProperties {

    private String secret;
    private String issuer;

    public String getSecret() {
        return secret;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}