package en.capstone.backend.controller;

import en.capstone.backend.api.Credentials;
import en.capstone.backend.api.User;
import en.capstone.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;


@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping("me")
    public User getLoggedInUser(Principal principal) {
        String username = principal.getName();
        System.out.println(principal.toString());
        return User.builder()
                .username(username)
                .build();
    }

    @PostMapping("login")
    public String login(@RequestBody Credentials credentials) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword()
        );

        try{
            authenticationManager.authenticate(authToken);

            String token = jwtService.createJwtToken(credentials.getUsername());

            return token;
        }catch(AuthenticationException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
