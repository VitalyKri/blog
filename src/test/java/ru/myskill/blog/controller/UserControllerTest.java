package ru.myskill.blog.controller;

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
import org.springframework.transaction.annotation.Transactional;
import ru.myskill.blog.AbstractTest;
import ru.myskill.blog.api.UserDto;
import ru.myskill.blog.api.mapping.UserMapper;
import ru.myskill.blog.entity.User;
import ru.myskill.blog.repository.UserDao;


/**
 * @author Vitaly Krivobokov
 * @Date 14.03.2023
 */
@ExtendWith(MockitoExtension.class)
class UserControllerTest extends AbstractTest {


    @Autowired
    UserDao userDao;

    @Autowired
    UserMapper userMapper;

    @Test
    @Order(0)
    void createUser() throws Exception {
       String request= objectMapper.writeValueAsString(userMapper.toUserDto(TestConstants.USER));
       mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.equalTo("Пользователь сохранен")));
    }

    @Test
    @Order(1)
    void getAllUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    @Order(2)
    void getUserByNickname() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/user/"+TestConstants.USER.getNickname()));
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        User user = objectMapper.readValue(contentAsString, User.class);
        Assertions.assertEquals(user.getPhone(),TestConstants.USER.getPhone());
    }

    @Test
    @Order(3)
    void deleteUser() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/user/"+TestConstants.USER.getNickname()));
        MockHttpServletResponse response = perform.andReturn().getResponse();


        UserDto user = objectMapper.readValue(response.getContentAsString(), UserDto.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/"+user.getNickname()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.equalTo("Пользователь удален")));
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }
}