package ru.myskill.blog.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;


@Component
@ConfigurationProperties("mytest")
public class ServerConfig {

    private Map<String, String> env;

    public Map<String, String> getEnv() {
        return env;
    }

    public void setEnv(Map<String, String> env) {
        this.env = env;
    }
}
