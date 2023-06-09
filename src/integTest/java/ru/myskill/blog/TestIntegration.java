package ru.myskill.blog;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import ru.myskill.blog.Config.AbstractTestContainerSetup;
import ru.myskill.blog.Config.TestConfig;
import ru.myskill.blog.api.UserDto;
import ru.myskill.blog.api.UserGateway;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Vitaly Krivobokov
 * @Date 05.04.2023
 */
@SpringBootTest
@Import(TestConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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


    @Test
    @Order(1)
    void createUser() {

        ResponseEntity<String> responseEntity = userGateway.saveUser(userDto);
        String body = responseEntity.getBody();
        assertEquals(body, "Пользователь сохранен");
    }

    @Test
    @Order(2)
    void findUser(){
        ResponseEntity<List<UserDto>> response = userGateway.getAllUser();
        List<UserDto> body = response.getBody();
        assertNotEquals(body.size(),0);
        assertEquals(body.get(0),userDto);

        ResponseEntity<UserDto> userBynickname = userGateway.getUserBynickname(userDto.getNickname());
        assertEquals(userBynickname.getBody(),userDto);
    }

    @Test
    @Order(3)
    void deleteUser(){
        ResponseEntity<String> responseEntity = userGateway.deleteUser(userDto.getNickname());
        assertEquals(responseEntity.getBody(),"Пользователь удален");
        ResponseEntity<List<UserDto>> responseAllUsers = userGateway.getAllUser();
        assertEquals(responseAllUsers.getBody().size(),0);

    }

}
