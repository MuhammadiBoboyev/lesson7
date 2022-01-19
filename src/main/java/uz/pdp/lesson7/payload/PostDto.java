package uz.pdp.lesson7.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    @NotBlank(message = "title is not empty")
    private String title;
    @NotBlank(message = "text is not empty")
    private String text;
    @NotBlank(message = "url is not empty")
    private String url;
}
