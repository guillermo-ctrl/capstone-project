package en.capstone.backend.repo;

import en.capstone.backend.model.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface DocumentRepo extends JpaRepository<DocumentEntity,Long> {

    List<DocumentEntity> getImageEntitiesByOwnerId(Long id);

    DocumentEntity getImageEntityByImageId(String imageId);
}