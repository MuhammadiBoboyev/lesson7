package uz.pdp.lesson7.exeptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
@Data
public class ForbiddenExeption extends RuntimeException{

    private String type;
    private String message;

    public ForbiddenExeption(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
