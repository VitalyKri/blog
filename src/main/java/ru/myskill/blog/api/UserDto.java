package ru.myskill.blog.api;


import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.*;


import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


/**
 * @author Vitaly Krivobokov
 * @Date 13.03.2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private UUID id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;

    private String city;
    private UUID picture_id;
    private String aboutMe;
    private String nickname;
    @Email
    private String email;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(nickname,userDto.nickname);

    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }

}
