package com.example.entity.socialLogin.oauth.util;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    String secretKey;

}
