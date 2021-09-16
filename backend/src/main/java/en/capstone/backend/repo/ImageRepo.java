package en.capstone.backend.repo;

import en.capstone.backend.model.ImageEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface ImageRepo extends PagingAndSortingRepository<ImageEntity,String> {

    List<ImageEntity> getImageEntitiesByOwnerId(Long id);

    ImageEntity getImageEntityByImageId(String imageId);
}