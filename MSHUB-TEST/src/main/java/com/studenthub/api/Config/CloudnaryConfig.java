package com.studenthub.api.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudnaryConfig {


    @Value("${CLOUDINARY_CLOUD_NAME}")
    private String CloudName;

    @Value("${CLOUDINARY_API_KEY}")
    private String APIKey;

    @Value("${CLOUDINARY_API_SECRET}")
    private String APIsecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CloudName,
                "api_key", APIKey,
                "api_secret", APIsecret));
    }
}
