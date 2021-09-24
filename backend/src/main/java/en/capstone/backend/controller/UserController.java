package en.capstone.backend.controller;

import en.capstone.backend.api.User;
import en.capstone.backend.model.UserEntity;
import en.capstone.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.notFound;
import java.util.Optional;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create a user")
    @PostMapping("/data")
    public ResponseEntity<User> create(@RequestBody User user) {
        String userName = user.getUsername();
        String password = user.getPassword();
        if (userName != null && userName.length()> 0) {
            UserEntity userEntity = userService.create(userName, password);
            User createdUser = new User(userEntity.getUser_id(), userEntity.getUsername(), userEntity.getPassword());
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @ApiOperation(value= "Get a user by ID")
    @GetMapping("/data/userid/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "ID not in database")
    })
    public ResponseEntity<User> getById(@PathVariable Long id) throws NotFoundException {
        Optional<UserEntity> optionalUserEntity = userService.findById(id);
        if (optionalUserEntity.isEmpty()) {
            return notFound().build();
        };
        UserEntity userEntity = optionalUserEntity.get();
        User createdUser = new User(userEntity.getUser_id(), userEntity.getUsername(), userEntity.getPassword());
        return ok(createdUser);
    }

    @ApiOperation(value= "Get a user by username")
    @GetMapping("/data/username/{username}")
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "User not in database")
    })
    public ResponseEntity<User> getByUsername(@PathVariable String username, @AuthenticationPrincipal UserEntity user) throws NotFoundException {
        Optional<UserEntity> optionalUserEntity = userService.findByUsername(username);
        if (optionalUserEntity.isEmpty()) {
            return notFound().build();
        };
        UserEntity userEntity = optionalUserEntity.get();
        User createdUser = new User(userEntity.getUser_id(), userEntity.getUsername(), userEntity.getPassword());
        return ok(createdUser);
    }

    @ApiOperation(value= "Get the user_id by username")
    @GetMapping("/data/getUserId/{username}")
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "User not in database")
    })
    public ResponseEntity<User> getIdByUsername(@PathVariable String username, @AuthenticationPrincipal UserEntity user) throws NotFoundException {
        Optional<UserEntity> optionalUserEntity = userService.findByUsername(username);
        if (optionalUserEntity.isEmpty()) {
            return notFound().build();
        };
        UserEntity userEntity = optionalUserEntity.get();
        User createdUser = User.builder()
                .user_id(userEntity.getUser_id())
                .username(userEntity.getUsername())
                .password(null)
                .build();
        return ok(createdUser);
    }

}
