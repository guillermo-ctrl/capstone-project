package en.capstone.backend.service;

import en.capstone.backend.model.UserEntity;
import en.capstone.backend.repo.UserRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity create(String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(name.trim());
        return userRepo.save(userEntity);
    }
}
