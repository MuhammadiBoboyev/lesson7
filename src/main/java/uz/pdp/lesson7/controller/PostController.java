package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.PostDto;
import uz.pdp.lesson7.payload.UserDto;
import uz.pdp.lesson7.service.PostService;
import uz.pdp.lesson7.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping("/addPost")
    public HttpEntity<?> addPost(@Valid @RequestBody PostDto postDto) {
        ApiResponse apiResponse = postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @PutMapping("/editPost/{id}")
    public HttpEntity<?> editPost(@PathVariable long id, @Valid @RequestBody PostDto postDto) {
        ApiResponse apiResponse = postService.editPost(id, postDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
