package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.aop.CheckPermission;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.RoleDto;
import uz.pdp.lesson7.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping("/addRole")
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission(value = "hasAuthority('EDIT_ROLE')")
    @PutMapping("/editRole/{id}")
    public HttpEntity<?> editRole(@PathVariable long id, @Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.editRole(id, roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
