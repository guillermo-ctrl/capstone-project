package en.capstone.backend.controller;

import en.capstone.backend.api.AccessToken;
import en.capstone.backend.api.Credentials;
import en.capstone.backend.api.User;
import en.capstone.backend.model.UserEntity;
import en.capstone.backend.service.JwtService;
import en.capstone.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
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
    public ResponseEntity<AccessToken> login(@RequestBody Credentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );

        try{
            authenticationManager.authenticate(authToken);
            UserEntity user = userService.find(username).orElseThrow();
            String token = jwtService.createJwtToken(user);

            return  ok(new AccessToken(token));
        }catch(AuthenticationException ex){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    }







