package en.capstone.backend.filter;

import en.capstone.backend.model.UserEntity;
import en.capstone.backend.service.JwtService;
import en.capstone.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userservice;

    @Autowired
    public JwtAuthFilter(JwtService jwtService, UserService userservice) {
        this.jwtService = jwtService;
        this.userservice = userservice;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && !authHeader.isBlank()) {
                String token = authHeader.replace("Bearer", "").trim();
                if (token != null) {
                    String username = jwtService.decodeUserName(token);
                    Optional<UserEntity> theUser = userservice.findByUsername(username);
                    if (theUser.isPresent()) {
                        SecurityContextHolder.getContext().setAuthentication(
                                new UsernamePasswordAuthenticationToken(
                                        theUser.get(),
                                        null,
                                        List.of(new SimpleGrantedAuthority("user"))
                                )
                        );
                    }
                }
            }
        } catch (Exception e) {
            //ignore
        }
        filterChain.doFilter(request, response);
    }
}
