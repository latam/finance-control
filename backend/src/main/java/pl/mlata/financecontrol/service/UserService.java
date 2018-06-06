package pl.mlata.financecontrol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mlata.financecontrol.configuration.security.authentication.JwtTokenAuthentication;
import pl.mlata.financecontrol.persistence.model.User;
import pl.mlata.financecontrol.persistence.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User getByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found."));
    }

//    @Transactional
//    public void registerNewAccount(RegistrationDTO registrationData) {
//        User user = new User(registrationData);
//        Company company = registrationData.getCompany();
//        String encodedPassword = passwordEncoder.encode(registrationData.getPassword());
//        user.setPassword(encodedPassword);
//
//        user = userRepository.save(user);
//
//        company.setUser(user);
//        company.setActive(true);
//        companyRepository.save(company);
//    }

    public User getCurrentUser() {
        JwtTokenAuthentication userAuth = (JwtTokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userAuth.getPrincipal();

        return user;
    }
}
