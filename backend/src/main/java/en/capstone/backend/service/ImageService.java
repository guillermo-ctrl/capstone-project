package en.capstone.backend.service;

import en.capstone.backend.model.ImageEntity;
import en.capstone.backend.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    private final ImageRepo imageRepo;

    @Autowired
    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public List<ImageEntity> getAllByUserId(Long id) {
        return imageRepo.getImageEntitiesByOwnerId(id);
    }

    public ImageEntity getImageByImageId(String imageId) {
        return imageRepo.getImageEntityByImageId(imageId);
    }
}
