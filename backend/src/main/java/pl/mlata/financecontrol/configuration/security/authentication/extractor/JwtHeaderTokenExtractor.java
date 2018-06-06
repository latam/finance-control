package pl.mlata.financecontrol.configuration.security.authentication.extractor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.mlata.financecontrol.configuration.security.authentication.JwtSettings;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtHeaderTokenExtractor implements TokenExtractor {
    private final JwtSettings jwtSettings;

    @Override
    public String extract(String payload) {
        if(StringUtils.isEmpty(payload)) {
            throw new AuthenticationServiceException("Authorization header cannot be empty.");
        }

        if(payload.length() < jwtSettings.getPrefix().length()) {
            throw new AuthenticationServiceException("Invalid authorization header length.");
        }

        return payload.substring(jwtSettings.getPrefix().length(), payload.length());
    }
}
