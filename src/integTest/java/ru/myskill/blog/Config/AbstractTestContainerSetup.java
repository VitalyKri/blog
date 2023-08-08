package ru.myskill.blog.Config;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.testcontainers.containers.ContainerState;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.IOException;

/**
 * @author Vitaly Krivobokov
 * @Date 05.04.2023
 */

@Testcontainers
@Execution(ExecutionMode.SAME_THREAD)
@AllArgsConstructor
public class AbstractTestContainerSetup {

    @Container
    private static DockerComposeContainer dockerComposeContainer = new
            DockerComposeContainer(new File("cicd/docker/docker-compose.yml"))
            .withLocalCompose(true)
            .withPull(false)
            .withTailChildContainers(false)
            .withServices("postgres_blog", "liquidbase_blog", "blog")
            .waitingFor("blog", Wait.forHttp("/user").forStatusCode(200));

}
