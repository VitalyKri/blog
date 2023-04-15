package ru.myskill.blog.controller;


import ru.myskill.blog.api.UserDto;
import ru.myskill.blog.entity.User;

import java.time.LocalDate;
import java.util.UUID;

public class TestConstants {

    public static final UserDto USER = UserDto.builder()
            .id(UUID.randomUUID())
            .firstName("Иван")
            .lastName("Ивано")
            .aboutMe("Тра-лала")
            .city("Тра-лала")
            .dateOfBirth(LocalDate.now())
            .email("fdfd@df.com")
            .gender("man")
            .nickname("QQQQ")
            .phone("+79999999999")
            .build();

}
