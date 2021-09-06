package en.capstone.backend.controller;

import en.capstone.backend.api.UserDto;
import en.capstone.backend.model.UserEntity;
import en.capstone.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Persist into Database")
    @PostMapping("/data")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        String name = userDto.getName();
        if (name != null && name.length()> 0) {
            UserEntity userEntity = userService.create(name);
            UserDto createdUserDto = new UserDto(userEntity.getId(), userEntity.getUsername());
            return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

}
