package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Post;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.PostDto;
import uz.pdp.lesson7.repository.PostRepository;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public ApiResponse addPost(PostDto postDto) {
        postRepository.save(new Post(postDto.getTitle(), postDto.getText(), postDto.getUrl()));
        return new ApiResponse("success added", true);
    }

    public ApiResponse editPost(long id, PostDto postDto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.get();
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("success edited", true);
    }
}
