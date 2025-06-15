package com.kwizera.springbootlab11securedprojecttracker.security;

import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;
import com.kwizera.springbootlab11securedprojecttracker.services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomOAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final UserServices userServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        User user = userServices.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        response.setContentType("text/html");
        response.getWriter().write("""
                <html>
                  <body>
                    <h2>Login successful!</h2>
                    <p>Here is your JWT token:</p>
                    <textarea rows="10" cols="100">Bearer %s</textarea>
                    <p>Copy the token above and paste it into Swagger UI.</p>
                    <a href="/swagger-ui/index.html">Go to Swagger UI</a>
                  </body>
                </html>
                """.formatted(token));
    }
}
