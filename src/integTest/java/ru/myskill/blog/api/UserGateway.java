package ru.myskill.blog.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vitaly Krivobokov
 * @Date 05.04.2023
 */
@Component
@FeignClient(name = "${Mytest.blog-feignClient.user.name}",
url = "${Mytest.blog-feignClient.url}"+"${Mytest.blog-feignClient.user.url}")
public interface UserGateway {

    @PostMapping
    ResponseEntity<String> saveUser(@RequestBody UserDto userDto);

    @GetMapping
    ResponseEntity<List<UserDto>> getAllUser();


    @GetMapping("/{nickname}")
    ResponseEntity<UserDto> getUserBynickname(@PathVariable String nickname);


    @DeleteMapping("/{nickname}")
    ResponseEntity<String> deleteUser(@PathVariable String nickname);
}

