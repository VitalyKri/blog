package ru.myskill.blog.docker;

import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;

/**
 * @author Vitaly Krivobokov
 * @Date 05.04.2023
 */
public class AbstractTestContainerSetup extends DockerComposeContainer {

    private static String pathDockerCompose = "cicd/docker/docker-compose.yml" ;

    private static DockerComposeContainer dockerComposeContainer;

    public AbstractTestContainerSetup(File... composeFiles) {
        super(composeFiles);
    }

    public static synchronized DockerComposeContainer getInstance() {

        if (dockerComposeContainer == null) {
            dockerComposeContainer = new
                    AbstractTestContainerSetup(new File(pathDockerCompose))
                    .withLocalCompose(true)
                    .withPull(false)
                    .withTailChildContainers(false)
                    .withServices("liquidbase_blog","blog")
                    .withEnv(System.getenv())
                    .waitingFor("blog", Wait.forHttp("/user").forStatusCode(200));
        }
        return dockerComposeContainer;
    }
    

    @Override
    public void stop() {}

}
