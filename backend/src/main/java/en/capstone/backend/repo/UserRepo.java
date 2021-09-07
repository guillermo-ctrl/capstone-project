package en.capstone.backend.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import en.capstone.backend.model.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {


}
