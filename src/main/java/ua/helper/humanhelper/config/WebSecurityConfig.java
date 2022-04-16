package ua.helper.humanhelper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ua.helper.humanhelper.model.CustomOAuth2User;
import ua.helper.humanhelper.service.impl.CustomOAuth2UserService;
import ua.helper.humanhelper.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public WebSecurityConfig(CustomOAuth2UserService oauthUserService, UserServiceImpl userService) {
        this.oauthUserService = oauthUserService;
        this.userService = userService;
    }

    private final CustomOAuth2UserService oauthUserService;
    private final UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

                        userService.processOAuthPostLogin(oauthUser);

                        response.sendRedirect("/list");
                    }
                });
    }
}
