package en.capstone.backend.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String userName;
    private String password;


    public UserDto(String userName, String password, Long id) {
        this.password = password;
        this.userName = userName;
        this.id = id;
    }
}
