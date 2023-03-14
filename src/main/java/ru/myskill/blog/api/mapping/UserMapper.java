package ru.myskill.blog.api.mapping;

import org.mapstruct.Mapper;
import ru.myskill.blog.api.UserDto;
import ru.myskill.blog.entity.User;

/**
 * @author Vitaly Krivobokov
 * @Date 14.03.2023
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

}
