package uz.pdp.lesson7.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lesson7.entity.enums.Permission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDto {
    @NotBlank(message = "role name is not empty")
    private String name;

    private String description;

    @NotEmpty(message = "role permission is not empty")
    private Set<Permission> permissions;
}
