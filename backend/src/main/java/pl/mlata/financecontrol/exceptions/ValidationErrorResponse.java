package pl.mlata.financecontrol.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse {
    private List<ValidationError> validationErrors;

    public ValidationErrorResponse(HttpStatus status, String message, String devMessage, ErrorCode errorCode, List<ValidationError> validationErrors) {
        super(status, message, devMessage, errorCode);
        this.validationErrors = validationErrors;
    }
}
