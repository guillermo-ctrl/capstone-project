package en.capstone.backend.controller;

import en.capstone.backend.api.UserDto;
import en.capstone.backend.model.UserEntity;
import en.capstone.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        String userName = userDto.getUserName();
        String password = userDto.getPassword();
        if (userName != null && userName.length()> 0) {
            UserEntity userEntity = userService.create(userName, password);
            UserDto createdUserDto = new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
            return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @ApiOperation(value= "Get a user by ID")
    @GetMapping("/data/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "ID not in database")
    })
    public ResponseEntity<UserDto> getById(@PathVariable Long id) throws NotFoundException {
        Optional<UserEntity> optionalUserEntity = userService.findById(id);
        if (optionalUserEntity.isEmpty()) {
            return notFound().build();
        };
        UserEntity userEntity = optionalUserEntity.get();
        UserDto createdUserDto = new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
        return ok(createdUserDto);
    }
}
