package ru.myskill.blog.controller;


import ru.myskill.blog.entity.User;

import java.time.LocalDate;

public class TestConstants {

    public static final User USER = User.builder()
            .firstName("Иван")
            .lastName("Ивано")
            .aboutMe("Тра-лала")
            .city("Тра-лала")
            .dateOfBirth(LocalDate.now())
            .email("fdfd@df.com")
            .gender("man")
            .isDeleted(false)
            .nickname("QQQQ")
            .phone("+79999999999")
            .build();

}
