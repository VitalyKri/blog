package ru.myskill.blog.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(name = "${Mytest.blog-feignClient.user.name}",
        url = "${Mytest.blog-feignClient.url}" + "${Mytest.blog-feignClient.user.url}")
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

