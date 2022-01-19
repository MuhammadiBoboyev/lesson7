package uz.pdp.lesson7.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDto {
    @NotNull(message = "fullName bo'sh bo'lmasligi kerak")
    private String fullName;

    @NotNull(message = "username bo'sh bo'lmasligi kerak")
    private String username;

    @NotNull(message = "password bo'sh bo'lmasligi kerak")
    private String password;

    @NotNull(message = "prePassword bo'sh bo'lmasligi kerak")
    private String prePassword;
}
