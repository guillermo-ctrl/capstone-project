package en.capstone.backend.controller;

import en.capstone.backend.api.AccessToken;
import en.capstone.backend.api.Credentials;
import en.capstone.backend.config.JwtConfig;
import static org.hamcrest.Matchers.nullValue;

import en.capstone.backend.model.UserEntity;
import en.capstone.backend.repo.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.hamcrest.MatcherAssert.assertThat;
import static en.capstone.backend.controller.AuthController.ACCESS_TOKEN_URL;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        properties = "spring.profiles.active:h2",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)

class AuthControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtConfig jwtConfig;

    private String url() {
        return "http://localhost:" + port + "/auth" +ACCESS_TOKEN_URL;
    }

    @AfterEach
    public void clearDb() {
        userRepo.deleteAll();
    }

    @Test
    public void getTokenOnSuccessfulLogin() {

        UserEntity user = new UserEntity();
        user.setUsername("username");
        user.setPassword(passwordEncoder.encode("password"));
        userRepo.saveAndFlush(user);

        // GIVEN
        Credentials credentials = Credentials.builder()
                .username(user.getUsername())
                .password("password").build();

        // WHEN
        ResponseEntity<AccessToken> response = restTemplate.postForEntity(
                        url(),
                        new HttpEntity<>(credentials),
                        AccessToken.class);
        // THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertNotNull(response.getBody());

        String token = response.getBody().getToken();
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token).getBody();
        assertThat(claims.getSubject(), is(user.getUsername()));


    }

    @Test
    public void wrongPasswordSoNoToken() {

        UserEntity user = new UserEntity();
        user.setUsername("username");
        user.setPassword(passwordEncoder.encode("password"));
        userRepo.saveAndFlush(user);

        // GIVEN
        Credentials credentials = Credentials.builder()
                .username("badUserName")
                .password("badPassword").build();

        // WHEN
        ResponseEntity<AccessToken> response = restTemplate.postForEntity(
                url(),
                new HttpEntity<>(credentials),
                AccessToken.class);

        // THEN
        assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }


}
