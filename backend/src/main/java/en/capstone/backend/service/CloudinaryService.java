package en.capstone.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import en.capstone.backend.api.CloudinaryCredentials;
import en.capstone.backend.model.DocumentEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@Service
public class CloudinaryService {

    CloudinaryCredentials cloudinaryCredentials = new CloudinaryCredentials();

    Cloudinary cloudinary = new Cloudinary(
            cloudinaryCredentials.getCredentials
    );

    public DocumentEntity uploadDocument(File document) throws IOException {
        Map response = cloudinary.uploader().upload(document, ObjectUtils.emptyMap());
        String url = response.get("url").toString();
        return DocumentEntity.builder().url(url).build();
    }

    public void deleteDocument(Long imageId) throws IOException {
        Map response = cloudinary.uploader().destroy(imageId.toString(), ObjectUtils.emptyMap());
        if(!response.get("result").toString().equals("ok")){
            throw new RuntimeException("Photo not found: "+imageId);
        }
    }

}
