package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Role;
import uz.pdp.lesson7.entity.User;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.UserDto;
import uz.pdp.lesson7.repository.RoleRepository;
import uz.pdp.lesson7.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse addUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername()))
            return new ApiResponse("username already exists", false);
        Optional<Role> optionalRole = roleRepository.findById(userDto.getRoleId().longValue());
        if (!optionalRole.isPresent())
            return new ApiResponse("role not found", false);
        userRepository.save(
                new User(
                        userDto.getFullName(),
                        userDto.getUsername(),
                        passwordEncoder.encode(userDto.getPassword()),
                        optionalRole.get(),
                        true
                )
        );
        return new ApiResponse("success added", true);
    }

    public ApiResponse editUser(long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found", false);
        if (!optionalUser.get().getUsername().equals(userDto.getUsername()))
            if (userRepository.existsByUsername(userDto.getUsername()))
                return new ApiResponse("username already exists", false);
        Optional<Role> optionalRole = roleRepository.findById(userDto.getRoleId().longValue());
        if (!optionalRole.isPresent())
            return new ApiResponse("role not found", false);
        User user = optionalUser.get();
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(optionalRole.get());
        userRepository.save(user);
        return new ApiResponse("success edited", true);
    }
}
