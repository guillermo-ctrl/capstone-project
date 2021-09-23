package en.capstone.backend.service;

import en.capstone.backend.model.DocumentEntity;
import en.capstone.backend.model.UserEntity;
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

    public List<DocumentEntity> getAllByUser(UserEntity user) {
        return documentRepo.findAllByUserIs(user);
    }

    public DocumentEntity getImageByImageId(String imageId, UserEntity user) {
        return documentRepo.findByImageIdAndUserIs(Long.valueOf(imageId), user);
    }

    public DocumentEntity saveFromUrl(String imageUrl, UserEntity user) {
        DocumentEntity documentEntity = new DocumentEntity(imageUrl, user);
        documentEntity.setUser(user);
        return documentRepo.save(documentEntity);
    }
}
