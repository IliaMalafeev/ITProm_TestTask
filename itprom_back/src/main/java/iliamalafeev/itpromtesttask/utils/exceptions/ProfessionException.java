package iliamalafeev.itpromtesttask.utils.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProfessionException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ProfessionException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
