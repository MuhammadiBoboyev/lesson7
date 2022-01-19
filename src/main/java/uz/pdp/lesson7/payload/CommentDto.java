package uz.pdp.lesson7.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {
    @NotBlank(message = "comment is not empty")
    private String text;
    @NotBlank(message = "Post id is not empty")
    private Long postId;
}
