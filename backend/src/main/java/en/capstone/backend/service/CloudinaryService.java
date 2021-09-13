package en.capstone.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import en.capstone.backend.model.ImageEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {
    Cloudinary cloudinary = new Cloudinary(
            /*
            cloudinary credentials here
            */);

    public ImageEntity uploadImage(File image) throws IOException {
        Map response = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
        String url = response.get("url").toString();
        String public_id = response.get("public_id").toString();
        return ImageEntity.builder().imageId(public_id).url(url).build();
    }

    public void deleteImage(Long imageId) throws IOException {
        Map response = cloudinary.uploader().destroy(imageId.toString(), ObjectUtils.emptyMap());
        if(!response.get("result").toString().equals("ok")){
            throw new RuntimeException("Photo not found: "+imageId);
        }
    }

}
