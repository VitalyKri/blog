package ru.myskill.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.myskill.blog.api.UserDto;
import ru.myskill.blog.api.mapping.UserMapper;
import ru.myskill.blog.entity.User;
import ru.myskill.blog.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vitaly Krivobokov
 * @Date 13.03.2023
 */


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Добавление пользователя")
    @PostMapping
    public String saveUser(@RequestBody @Validated UserDto userDto) throws Exception {
        User user = userMapper.toUser(userDto);
        userService.saveUser(user);
        return "Пользователь сохранен";
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping
    public List<UserDto> getAllUser(){
        List<User> allUsers = userService.getAllUsers();
        return allUsers.stream()
                .map(user -> userMapper.toUserDto(user))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Получение пользователя по nickname")
    @GetMapping("/{nickname}")
    public UserDto getUserBynickname(@PathVariable String nickname){
        User userById = userService.getUserByNickname(nickname);
        return userMapper.toUserDto(userById);
    }

    @Operation(summary = "Удаление пользователя по nickname")
    @DeleteMapping("/{nickname}")
    public String deleteUser(@PathVariable String nickname){
        userService.deactivateUserByNickname(nickname);
        return "Пользователь удален";
    }
}
