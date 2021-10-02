package en.capstone.backend.service;

import en.capstone.backend.api.Document;
import en.capstone.backend.api.FilterParams;
import en.capstone.backend.model.DocumentEntity;
import en.capstone.backend.model.UserEntity;
import en.capstone.backend.repo.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepo documentRepo;

    @Autowired
    public DocumentService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    public DocumentEntity save(DocumentEntity documentEntity, UserEntity user) {
        return documentRepo.save(documentEntity);
    }

    public List<DocumentEntity> getAllByUser(UserEntity user) {
        List<DocumentEntity> allByUserIs = documentRepo.findAllByUserIs(user);
        return allByUserIs;
    }

    public void deleteByImageIdAndUserIs(String documentId, UserEntity user) {
        documentRepo.deleteByImageIdAndUserIs(Long.valueOf(documentId), user);
    }

    public DocumentEntity getImageByImageId(String imageId, UserEntity user) {
        return documentRepo.findByImageIdAndUserIs(Long.valueOf(imageId), user);
    }

    public DocumentEntity saveFromUrl(String imageUrl, UserEntity user) {
        DocumentEntity documentEntity = new DocumentEntity(imageUrl, user);
        documentEntity.setUser(user);
        return documentRepo.save(documentEntity);
    }

    public DocumentEntity getFromUrl(String imageUrl, UserEntity user) {
        return documentRepo.findByUrlAndUserIs(imageUrl, user);
    }

    public DocumentEntity updateDocument(Document document, UserEntity user) {

        DocumentEntity existingDocument = documentRepo.findByImageIdAndUserIs(Long.valueOf(document.getImageId()), user);
        existingDocument.setDocumentType(document.getDocumentType());
        existingDocument.setCategory(document.getCategory());
        existingDocument.setDate(document.getDate());
        existingDocument.setLanguage(document.getLanguage());
        existingDocument.setSender(document.getSender());
        existingDocument.setPhysicalLocation(document.getPhysicalLocation());
        existingDocument.setRecipient(document.getRecipient());
        existingDocument.setUser(user);
        existingDocument.setUrl(document.getUrl());

        return documentRepo.save(existingDocument);

    }


    public List<DocumentEntity> getFilteredDocs(FilterParams filterParams, UserEntity user) {
        String category = filterParams.getCategory();
        String date = filterParams.getDate();
        String documentType = filterParams.getDocumentType();
        String language = filterParams.getLanguage();
        String recipient = filterParams.getRecipient();
        String sender = filterParams.getSender();
        String physicalLocation = filterParams.getPhysicalLocation();

        List<DocumentEntity> filteredDocsList = documentRepo.findAllByUserIs(user);
        List<DocumentEntity> toDelete = new ArrayList<DocumentEntity>();

        for (DocumentEntity existingDoc : filteredDocsList) {

            //category
            if (!category.equals("")) { //is category an empty string?
                if(existingDoc.getCategory() == null) { //is existingDoc category null?
                    toDelete.add(existingDoc); //then delete because category has params and the document has it null
                }
                else if (!existingDoc.getCategory().equals(category)) {
                    toDelete.add(existingDoc);
                }
            }


            //date
            if (!date.equals("")) {
                if(existingDoc.getDate() == null) {
                    toDelete.add(existingDoc);
                }
                else if (!existingDoc.getDate().equals(date)) {
                    toDelete.add(existingDoc);
                }
            }


            //documentType
            if (!documentType.equals("")) {
                if(existingDoc.getDocumentType() == null) {
                    toDelete.add(existingDoc);
                }
                else if (!existingDoc.getDocumentType().equals(documentType)) {
                    toDelete.add(existingDoc);
                }
            }

            //language
            if (!language.equals("")) {
                if(existingDoc.getLanguage() == null) {
                    toDelete.add(existingDoc);
                }
                else if (!existingDoc.getDocumentType().equals(language)) {
                    toDelete.add(existingDoc);
                }
            }


            //recipient
            if (!recipient.equals("")) {
                if(existingDoc.getRecipient() == null) {
                    toDelete.add(existingDoc);
                }
                else if (!existingDoc.getRecipient().equals(recipient)) {
                    toDelete.add(existingDoc);
                }
            }


            //sender
            if (!sender.equals("")) {
                if(existingDoc.getSender() == null) {
                    toDelete.add(existingDoc);
                }
                else if (!existingDoc.getSender().equals(sender)) {
                    toDelete.add(existingDoc);
                }
            }


            //physicalLocation
            if (!physicalLocation.equals("")) {
                if(existingDoc.getPhysicalLocation() == null) {
                    toDelete.add(existingDoc);
                }
                else if (!existingDoc.getPhysicalLocation().equals(physicalLocation)) {
                    toDelete.add(existingDoc);
                }
            }


        }
        filteredDocsList.removeAll(toDelete);
        return filteredDocsList;
    }



}