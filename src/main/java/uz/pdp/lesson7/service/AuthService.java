package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.User;
import uz.pdp.lesson7.exeptions.ResourceNotFoundException;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.LoginDto;
import uz.pdp.lesson7.payload.RegisterDto;
import uz.pdp.lesson7.repository.RoleRepository;
import uz.pdp.lesson7.repository.UserRepository;
import uz.pdp.lesson7.security.JwtProvider;
import uz.pdp.lesson7.entity.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Lazy
    @Autowired
    AuthenticationManager authenticationManager;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse("parollar mos emas", false);
        if (userRepository.existsByUsername(registerDto.getUsername()))
            return new ApiResponse("bunday yuser mavjud", false);
        userRepository.save(new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByRoleName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("role", "roleName", AppConstants.USER)),
                true
        ));
        return new ApiResponse("success", true);
    }

    public ApiResponse loginUser(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            return new ApiResponse("token", true, jwtProvider.generateToken(user.getUsername(), user.getRole()));
        } catch (ResourceNotFoundException exception) {
            return new ApiResponse("password or login in correct", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("user", "username", username));
    }
}
