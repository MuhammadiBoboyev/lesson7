package uz.pdp.lesson7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.lesson7.entity.enums.Permission;
import uz.pdp.lesson7.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String roleName;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private Set<Permission> permissions;
}
