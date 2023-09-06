package ru.myskill.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.myskill.blog.api.FeignClientFactory;
import ru.myskill.blog.api.UserGateway;

@SpringJUnitConfig
@EnableFeignClients
@EnableConfigurationProperties
public class TestConfig {

    @Autowired
    private Environment env;

    @Autowired
    private FeignClientFactory feignClientFactory;

    @Bean
    public UserGateway userGateway() {
        String url = env.getProperty("Mytest.blog-feignClient.url") + env.getProperty("Mytest.blog-feignClient.user.url");
        return feignClientFactory.newFeignClient(UserGateway.class, url);
    }


}
