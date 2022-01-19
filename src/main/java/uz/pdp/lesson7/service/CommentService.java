package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Comment;
import uz.pdp.lesson7.entity.Post;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.CommentDto;
import uz.pdp.lesson7.repository.CommentRepository;
import uz.pdp.lesson7.repository.PostRepository;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    public ApiResponse addComment(CommentDto commentDto) {
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        commentRepository.save(new Comment(commentDto.getText(), optionalPost.get()));
        return new ApiResponse("success added", true);
    }

    public ApiResponse editComment(long id, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        Comment comment = optionalComment.get();
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
        return new ApiResponse("success edited", true);
    }
}
