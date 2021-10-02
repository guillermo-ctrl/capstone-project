package en.capstone.backend.controller;

import en.capstone.backend.api.Document;
import en.capstone.backend.api.FilterParams;
import en.capstone.backend.model.DocumentEntity;
import en.capstone.backend.model.UserEntity;
import en.capstone.backend.service.CloudinaryService;
import en.capstone.backend.service.DocumentService;
import en.capstone.backend.service.MapperService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("image")
public class DocumentController {

    private final DocumentService documentService;
    private final CloudinaryService cloudinaryService;
    private final MapperService mapperService;

    @Autowired
    public DocumentController(DocumentService documentService, CloudinaryService cloudinaryService, MapperService mapperService) {
        this.documentService = documentService;
        this.cloudinaryService = cloudinaryService;
        this.mapperService = mapperService;
    }

    @GetMapping("getallimages")
    public List<Document> getAllImageEntitiesFromUser(@AuthenticationPrincipal UserEntity user) {
        List<DocumentEntity> allDocEntities = documentService.getAllByUser(user);
        List<Document> allDocs = new ArrayList<>();
        for(DocumentEntity documentEntity : allDocEntities) {
            allDocs.add(mapperService.map(documentEntity));
        }
        return allDocs;
    }

    @GetMapping("getimagebyimageid/{imageId}")
    public Document getImageByImageId(@PathVariable String imageId, @AuthenticationPrincipal UserEntity user) {
        return mapperService.map(documentService.getImageByImageId(imageId, user));
    }

    @ApiOperation(value = "Upload an image to cloudinary")
    @PostMapping("upload_to_cloud")
    public DocumentEntity uploadDocumentToCloudinary(@RequestParam MultipartFile document, @AuthenticationPrincipal UserEntity user) throws IOException {
        File fileToUpload = File.createTempFile("document", null);
        document.transferTo(fileToUpload);
        return cloudinaryService.uploadDocument(fileToUpload);
    }

    @ApiOperation(value = "Get a list of images that conform to a filter")
    @PostMapping("get-filtered-images")
    public List<Document> getFilteredDocs(@RequestBody FilterParams filterParams, @AuthenticationPrincipal UserEntity user) {
        List<DocumentEntity> entityList = documentService.getFilteredDocs(filterParams, user);
        List<Document> documentList = new ArrayList<>();
        if(!entityList.isEmpty()) {for(DocumentEntity documentEntity : entityList) {
            documentList.add(mapperService.map(documentEntity));
        }}
        return documentList;
    }


    @ApiOperation(value = "Create a document in database")
    @PostMapping("save_in_database")
    public ResponseEntity<Document> saveDocumentInDocumentRepo(@RequestBody Document document, @AuthenticationPrincipal UserEntity user) {
        documentService.saveFromUrl(document.getUrl(), user);
        DocumentEntity createdDocumentEntity = documentService.getFromUrl(document.getUrl(), user);
        return new ResponseEntity<>(mapperService.map(createdDocumentEntity), HttpStatus.CREATED);

    }

    @ApiOperation(value = "Update a document")
    @PutMapping("update")
    public ResponseEntity<Document> updateDocument(@RequestBody Document document, @AuthenticationPrincipal UserEntity user) {
        documentService.updateDocument(document, user);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

}
