package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.RegisterDto;
import uz.pdp.lesson7.payload.UserDto;
import uz.pdp.lesson7.service.AuthService;
import uz.pdp.lesson7.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping("/addUser")
    public HttpEntity<?> addUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    @PutMapping("/editUser/{id}")
    public HttpEntity<?> editUser(@PathVariable long id, @Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.editUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
