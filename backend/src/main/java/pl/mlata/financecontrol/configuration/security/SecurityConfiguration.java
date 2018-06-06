package pl.mlata.financecontrol.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import pl.mlata.financecontrol.configuration.security.authentication.JwtAuthenticationProvider;
import pl.mlata.financecontrol.configuration.security.authentication.JwtLoginAuthenticationProvider;
import pl.mlata.financecontrol.configuration.security.authentication.JwtLoginFilter;
import pl.mlata.financecontrol.configuration.security.authentication.JwtTokenAuthenticationProcessingFilter;
import pl.mlata.financecontrol.configuration.security.authentication.extractor.TokenExtractor;
import pl.mlata.financecontrol.service.TokenAuthenticationService;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final RestAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final JwtLoginAuthenticationProvider jwtLoginAuthenticationProvider;
    private final AuthenticationFailureHandler failureHandler;
    private final TokenAuthenticationService tokenAuthenticationService;
    private final TokenExtractor tokenExtractor;
    private final CorsFilter corsFilter;

    private final String loginEntryPoint = "/api/auth/login";

    protected JwtLoginFilter buildJwtLoginFilter() throws Exception {
        JwtLoginFilter filter = new JwtLoginFilter(loginEntryPoint, this.authenticationManager(), failureHandler, tokenAuthenticationService);
        return filter;
    }

    protected JwtTokenAuthenticationProcessingFilter buildJwtAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(loginEntryPoint);
        SkipRequestPathMatcher matcher = new SkipRequestPathMatcher(pathsToSkip, "/api/**");
        JwtTokenAuthenticationProcessingFilter filter
                = new JwtTokenAuthenticationProcessingFilter(matcher, failureHandler, tokenExtractor);
        filter.setAuthenticationManager(this.authenticationManager());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
        auth.authenticationProvider(jwtLoginAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/api/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(loginEntryPoint, "/").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .addFilterBefore(
                        buildJwtLoginFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(
                        buildJwtAuthenticationProcessingFilter(),
                        UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(corsFilter, ChannelProcessingFilter.class);
    }

}
