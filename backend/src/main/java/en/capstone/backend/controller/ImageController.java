package en.capstone.backend.controller;
import en.capstone.backend.model.ImageEntity;
import en.capstone.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("getallimages/{ownerid}")
    public List<ImageEntity> getImageEntitiesByOwnerId(@PathVariable Long ownerid){
        return imageService.getAllByUserId(ownerid);
    }

}
