package en.capstone.backend.service;

import en.capstone.backend.model.UserEntity;
import en.capstone.backend.repo.UserRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity create(String name, String password) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(name.trim());

        userEntity.setPassword(password);
        return userRepo.save(userEntity);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public Optional<UserEntity> find(String username) {
        return userRepo.findByUsername(username);
    }
}
