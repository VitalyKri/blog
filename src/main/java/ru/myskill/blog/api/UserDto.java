package ru.myskill.blog.api;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.time.LocalDate;
import java.util.UUID;


/**
 * @author Vitaly Krivobokov
 * @Date 13.03.2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String city;
    private String picture_id;
    private String aboutMe;
    private String nickname;
    @Email
    private String email;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phone;
    private boolean isDelete;
}
