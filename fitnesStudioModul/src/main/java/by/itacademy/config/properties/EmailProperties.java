package by.itacademy.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email")
public class EmailProperties {

    private String textType;
    private String verificationFileName;
    private String registrationFileName;
    private String userNameParamName;
    private String mailAdressParamName;
    private String verificationCodeParamName;
    private int maxSend;

    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    public String getVerificationFileName() {
        return verificationFileName;
    }

    public void setVerificationFileName(String verificationFileName) {
        this.verificationFileName = verificationFileName;
    }

    public String getRegistrationFileName() {
        return registrationFileName;
    }

    public void setRegistrationFileName(String registrationFileName) {
        this.registrationFileName = registrationFileName;
    }

    public String getUserNameParamName() {
        return userNameParamName;
    }

    public void setUserNameParamName(String userNameParamName) {
        this.userNameParamName = userNameParamName;
    }

    public String getMailAdressParamName() {
        return mailAdressParamName;
    }

    public void setMailAdressParamName(String mailAdressParamName) {
        this.mailAdressParamName = mailAdressParamName;
    }

    public String getVerificationCodeParamName() {
        return verificationCodeParamName;
    }

    public void setVerificationCodeParamName(String verificationCodeParamName) {
        this.verificationCodeParamName = verificationCodeParamName;
    }

    public int getMaxSend() {
        return maxSend;
    }

    public void setMaxSend(int maxSend) {
        this.maxSend = maxSend;
    }
}