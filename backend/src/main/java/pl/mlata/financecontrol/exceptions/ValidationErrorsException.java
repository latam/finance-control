package pl.mlata.financecontrol.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorsException extends RuntimeException {
    private BindingResult result;
}
