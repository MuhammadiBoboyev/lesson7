package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Role;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.RoleDto;
import uz.pdp.lesson7.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleRepository getRoleRepository;

    public ApiResponse addRole(RoleDto roleDto) {
        if (roleRepository.existsByRoleName(roleDto.getName()))
            return new ApiResponse("rolename tizimda mavjud", false);
        roleRepository.save(new Role(roleDto.getName(), roleDto.getDescription(), roleDto.getPermissions()));
        return new ApiResponse("role saved", true);
    }

    public ApiResponse editRole(long id, RoleDto roleDto) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (!optionalRole.isPresent())
            return new ApiResponse("role not found", false);
        if (!(optionalRole.get().getRoleName().equals(roleDto.getName())))
            if (roleRepository.existsByRoleName(roleDto.getName()))
                return new ApiResponse("exist by role name", false);
        Role role = optionalRole.get();
        role.setRoleName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setPermissions(roleDto.getPermissions());
        roleRepository.save(role);
        return new ApiResponse("success edited", true);
    }
}
