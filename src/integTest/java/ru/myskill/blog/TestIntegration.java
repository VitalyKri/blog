package ru.myskill.blog;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.myskill.blog.docker.AbstractTestContainerSetup;
import ru.myskill.blog.config.TestConfig;
import ru.myskill.blog.api.UserDto;
import ru.myskill.blog.api.UserGateway;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(TestConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
public class TestIntegration extends AbstractTestContainerSetup {

    @Autowired
    UserGateway userGateway;

    private UserDto userDto = UserDto.builder()
            .id(UUID.randomUUID())
            .firstName("dfdsf")
            .lastName("sdfasd")
            .nickname("2123")
            .aboutMe("dfsdf")
            .email("gmail@gmail.com")
            .city("Уфа")
            .dateOfBirth(LocalDate.now())
            .build();

    @Container
    private static DockerComposeContainer<AbstractTestContainerSetup> dockerComposeContainer = AbstractTestContainerSetup.getInstance();

    @Test
    @Order(1)
    void createUser() {
        ResponseEntity<String> responseEntity = userGateway.saveUser(userDto);
        String body = responseEntity.getBody();
        assertEquals(body, "Пользователь сохранен");
    }

    @Test
    @Order(2)
    void findUser() throws NullPointerException {
        ResponseEntity<List<UserDto>> response = userGateway.getAllUser();
        List<UserDto> body = response.getBody();
        assertNotNull(body);
        assertNotEquals(body.size(), 0);
        assertEquals(body.get(0), userDto);

        ResponseEntity<UserDto> userBynickname = userGateway.getUserBynickname(userDto.getNickname());
        assertEquals(userBynickname.getBody(), userDto);
    }

    @Test
    @Order(3)
    void deleteUser() throws NullPointerException {
        ResponseEntity<String> responseEntity = userGateway.deleteUser(userDto.getNickname());
        assertEquals(responseEntity.getBody(), "Пользователь удален");
        ResponseEntity<List<UserDto>> responseAllUsers = userGateway.getAllUser();
        assertNotNull(responseAllUsers.getBody());
        assertEquals(responseAllUsers.getBody().size(), 0);

    }

}
