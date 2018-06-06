package pl.mlata.financecontrol.configuration.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pl.mlata.financecontrol.service.TokenAuthenticationService;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final TokenAuthenticationService tokenAuthenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtTokenAuthentication userPreAuth = (JwtTokenAuthentication) authentication;
        Authentication userAuth = tokenAuthenticationService.getAuthentication((String) userPreAuth.getCredentials());
        return userAuth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtTokenAuthentication.class.isAssignableFrom(authentication));
    }
}
