package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.CommentDto;
import uz.pdp.lesson7.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping("/addComment")
    public HttpEntity<?> addComment(@Valid @RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_COMMENT')")
    @PutMapping("/editComment/{id}")
    public HttpEntity<?> editComment(@PathVariable long id, @Valid @RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.editComment(id, commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
