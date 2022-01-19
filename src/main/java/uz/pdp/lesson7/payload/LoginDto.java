package uz.pdp.lesson7.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {
    @NotBlank(message = "username bo'sh bo'lmasligi kerak")
    private String username;
    @NotBlank(message = "password bo'sh bo'lmasligi kerak")
    private String password;
}
