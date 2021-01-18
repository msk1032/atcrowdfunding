package com.studyhub.crowd.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author haoren
 * @create 2021-01-18 9:18
 */
@Component
@ConfigurationProperties(prefix = "send.email")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailConfigProperties {

    private String sendEmailAccount;

    private String sendEmailPassword;

    private String sendEmailSMTPServer;

    private String smtpPort;

}
