package en.capstone.backend.repo;

import en.capstone.backend.api.User;
import en.capstone.backend.model.DocumentEntity;
import en.capstone.backend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DocumentRepo extends JpaRepository<DocumentEntity,Long>, JpaSpecificationExecutor<DocumentEntity> {

    List<DocumentEntity> findAllByUserIs(UserEntity user);

    DocumentEntity findByImageIdAndUserIs(Long imageId, UserEntity user);

    DocumentEntity findByUrlAndUserIs(String url, UserEntity user);

    DocumentEntity deleteByImageIdAndUserIs(Long imageId, UserEntity user);

}