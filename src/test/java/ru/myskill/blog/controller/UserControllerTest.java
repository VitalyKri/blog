package ru.myskill.blog.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.myskill.blog.AbstractTest;
import ru.myskill.blog.api.UserDto;
import ru.myskill.blog.api.mapping.UserMapper;
import ru.myskill.blog.repository.UserDao;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserControllerTest extends AbstractTest {


    @Autowired
    UserDao userDao;

    @Autowired
    UserMapper userMapper;

    @Test
    @Order(0)
    void createUser() throws Exception {
        String request = objectMapper.writeValueAsString(TestConstants.USER);
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.equalTo("Пользователь сохранен")));
    }

    @Test
    @Order(1)
    void getAllUser() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/user"));
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        String userListString = perform.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        List<UserDto> userList = objectMapper.readValue(userListString, new TypeReference<>() {
        });
        Assertions.assertEquals(userList.size(), 1);
        UserDto userDto = userList.get(0);

        Assertions.assertEquals(userDto.getPhone(), TestConstants.USER.getPhone());
        Assertions.assertEquals(userDto.getNickname(), TestConstants.USER.getNickname());
        Assertions.assertEquals(userDto.getAboutMe(), TestConstants.USER.getAboutMe());
        Assertions.assertEquals(userDto.getCity(), TestConstants.USER.getCity());
        Assertions.assertEquals(userDto.getEmail(), TestConstants.USER.getEmail());
        Assertions.assertEquals(userDto.getGender(), TestConstants.USER.getGender());
        Assertions.assertEquals(userDto.getDateOfBirth(), TestConstants.USER.getDateOfBirth());
        Assertions.assertEquals(userDto.getLastName(), TestConstants.USER.getLastName());
        Assertions.assertEquals(userDto.getFirstName(), TestConstants.USER.getFirstName());

    }

    @Test
    @Order(2)
    void getUserByNickname() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/user/" + TestConstants.USER.getNickname()));
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        String contentAsString = perform.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        UserDto user = objectMapper.readValue(contentAsString, UserDto.class);
        Assertions.assertEquals(user.getPhone(), TestConstants.USER.getPhone());
        Assertions.assertEquals(user.getNickname(), TestConstants.USER.getNickname());
        Assertions.assertEquals(user.getAboutMe(), TestConstants.USER.getAboutMe());
        Assertions.assertEquals(user.getCity(), TestConstants.USER.getCity());
        Assertions.assertEquals(user.getEmail(), TestConstants.USER.getEmail());
        Assertions.assertEquals(user.getGender(), TestConstants.USER.getGender());
        Assertions.assertEquals(user.getDateOfBirth(), TestConstants.USER.getDateOfBirth());
        Assertions.assertEquals(user.getLastName(), TestConstants.USER.getLastName());
        Assertions.assertEquals(user.getFirstName(), TestConstants.USER.getFirstName());
    }

    @Test
    @Order(3)
    void deleteUser() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/user/" + TestConstants.USER.getNickname()));
        MockHttpServletResponse response = perform.andReturn().getResponse();


        UserDto user = objectMapper.readValue(response.getContentAsString(), UserDto.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/" + user.getNickname()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.equalTo("Пользователь удален")));
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    @Order(4)
    void createEmptyUser() throws Exception {
        String request = objectMapper.writeValueAsString(new UserDto());
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                ).andReturn().getResponse();
        assertEquals(response.getStatus(), 400);

        request = objectMapper.writeValueAsString(UserDto.builder()
                .firstName("dfdf")
                .lastName("dfdf")
                .phone("111111111111111111111").build());
        response =
                mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                ).andReturn().getResponse();
        assertEquals(response.getStatus(), 400);

        request = objectMapper.writeValueAsString(UserDto.builder()
                .firstName("dfdf")
                .lastName("dfdf")
                .email("Непочта").build());
        response =
                mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                ).andReturn().getResponse();
        assertEquals(response.getStatus(), 400);


        request = objectMapper.writeValueAsString(UserDto.builder().firstName("dfdf").lastName("dfdf").build());
        response =
                mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                ).andReturn().getResponse();
        assertEquals(response.getStatus(), 200);

    }
}