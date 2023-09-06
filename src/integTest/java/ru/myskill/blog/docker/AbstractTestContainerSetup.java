package ru.myskill.blog.docker;


import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import java.io.File;


public class AbstractTestContainerSetup extends DockerComposeContainer<AbstractTestContainerSetup> {

    private static String pathDockerCompose = "cicd/docker/docker-compose.yml";

    private static DockerComposeContainer<AbstractTestContainerSetup> dockerComposeContainer;

    public AbstractTestContainerSetup(File... composeFiles) {
        super(composeFiles);
    }

    public static synchronized DockerComposeContainer<AbstractTestContainerSetup> getInstance() {

        if (dockerComposeContainer == null) {
            dockerComposeContainer = new
                    AbstractTestContainerSetup(new File(pathDockerCompose))
                    .withLocalCompose(true)
                    .withPull(false)
                    .withTailChildContainers(false)
                    .withServices("liquidbase_blog", "blog")
                    .withEnv(System.getenv())
                    .waitingFor("blog", Wait.forHttp("/user").forStatusCode(200));
        }
        return dockerComposeContainer;
    }


    @Override
    public void stop() {
    }

}
