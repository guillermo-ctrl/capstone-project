package en.capstone.backend.controller;
import en.capstone.backend.model.ImageEntity;
import en.capstone.backend.service.CloudinaryService;
import en.capstone.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.io.File;


@RestController
@RequestMapping("image")
public class ImageController {

    private final ImageService imageService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ImageController(ImageService imageService, CloudinaryService cloudinaryService) {
        this.imageService = imageService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("getallimages/{ownerid}")
    public List<ImageEntity> getImageEntitiesByOwnerId(@PathVariable Long ownerid){
        return imageService.getAllByUserId(ownerid);
    }

    @GetMapping("getimagebyimageid/{imageId}")
    public ImageEntity getImageByImageId(@PathVariable String imageId){
        return imageService.getImageByImageId(imageId);
    }
    @PostMapping("upload")
    public ImageEntity uploadImage(@RequestParam MultipartFile document) throws IOException {
        File fileToUpload = File.createTempFile("document", null);
        document.transferTo(fileToUpload);
        System.out.println(fileToUpload);
        return cloudinaryService.uploadImage(fileToUpload);
    }
}
