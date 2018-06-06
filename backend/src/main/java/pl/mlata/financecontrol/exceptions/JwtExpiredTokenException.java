package pl.mlata.financecontrol.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Getter
@Setter
public class JwtExpiredTokenException extends AuthenticationException {
    private String token;

    public JwtExpiredTokenException(String message) {
        super(message);
    }

    public JwtExpiredTokenException(String message, String token) {
        super(message);
        this.token = token;
    }
}
