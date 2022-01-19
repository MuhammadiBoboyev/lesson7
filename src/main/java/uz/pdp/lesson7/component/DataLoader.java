package uz.pdp.lesson7.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.lesson7.entity.Role;
import uz.pdp.lesson7.entity.User;
import uz.pdp.lesson7.repository.RoleRepository;
import uz.pdp.lesson7.repository.UserRepository;
import uz.pdp.lesson7.entity.utils.AppConstants;

import java.util.Arrays;
import java.util.HashSet;

import static uz.pdp.lesson7.entity.enums.Permission.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    @Override
    public void run(String... args) {
        if (initialMode.equals("always")) {
            Role admin = roleRepository.save(new Role(AppConstants.ADMIN, "system admin", new HashSet<>(Arrays.asList(values()))));
            Role user = roleRepository.save(new Role(AppConstants.USER, "simple user", new HashSet<>(Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT))));
            userRepository.save(new User("Admin", "Admin", passwordEncoder.encode("admin123"), admin, true));
            userRepository.save(new User("User", "User", passwordEncoder.encode("user123"), user, true));
        }
    }
}
