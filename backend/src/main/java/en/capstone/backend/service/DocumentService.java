package en.capstone.backend.service;

import en.capstone.backend.model.DocumentEntity;
import en.capstone.backend.repo.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepo documentRepo;

    @Autowired
    public DocumentService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    public List<DocumentEntity> getAllByUserId(Long id) {
        return documentRepo.getImageEntitiesByOwnerId(id);
    }

    public DocumentEntity getImageByImageId(String imageId) {
        return documentRepo.getImageEntityByImageId(imageId);
    }

    public DocumentEntity saveFromUrl (String imageUrl, Long ownerId) {
        DocumentEntity documentEntity = new DocumentEntity(imageUrl, ownerId);
        return documentRepo.save(documentEntity);
    }
}
