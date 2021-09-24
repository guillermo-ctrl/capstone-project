package en.capstone.backend.service;

import en.capstone.backend.api.Document;
import en.capstone.backend.model.DocumentEntity;
import en.capstone.backend.repo.DocumentRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class MapperService {

    private final DocumentRepo documentRepo;


    @Autowired
    public MapperService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    //Category
    public Document map(DocumentEntity documentEntity) {
        return Document.builder()
                .imageId(documentEntity.getImageId())
                .url(documentEntity.getUrl())
                .ownerId(documentEntity.getUser().getUser_id())
                .sender(documentEntity.getSender())
                .recipient(documentEntity.getRecipient())
                .date(documentEntity.getDate())
                .category(documentEntity.getCategory())
                .language(documentEntity.getLanguage())
                .documentType(documentEntity.getDocumentType())
                .physicalLocation(documentEntity.getPhysicalLocation())
                .build();
    }

}
