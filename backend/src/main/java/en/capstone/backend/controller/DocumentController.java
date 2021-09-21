package en.capstone.backend.controller;
import en.capstone.backend.api.Document;
import en.capstone.backend.model.DocumentEntity;
import en.capstone.backend.service.CloudinaryService;
import en.capstone.backend.service.DocumentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.io.File;


@RestController
@RequestMapping("image")
public class DocumentController {

    private final DocumentService documentService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public DocumentController(DocumentService documentService, CloudinaryService cloudinaryService) {
        this.documentService = documentService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("getallimages/{ownerid}")
    public List<DocumentEntity> getImageEntitiesByOwnerId(@PathVariable Long ownerid){
        return documentService.getAllByUserId(ownerid);
    }

    @GetMapping("getimagebyimageid/{imageId}")
    public DocumentEntity getImageByImageId(@PathVariable String imageId){
        return documentService.getImageByImageId(imageId);
    }

    @ApiOperation(value = "Upload an image to cloudinary")
    @PostMapping("upload_to_cloud")
    public DocumentEntity uploadDocumentToCloudinary(@RequestParam MultipartFile document) throws IOException {
        File fileToUpload = File.createTempFile("document", null);
        document.transferTo(fileToUpload);
        return cloudinaryService.uploadDocument(fileToUpload);
    }

    @ApiOperation(value = "Create a document in database")
    @PostMapping("save_in_database")
    public ResponseEntity<Document> saveDocumentInDocumentRepo(@RequestBody Document document) {
        documentService.saveFromUrl(document.getUrl(), document.getOwnerId());
        return new ResponseEntity<>(document, HttpStatus.CREATED);

    }
}
