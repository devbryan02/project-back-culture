package com.project.projectbackculture.external;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.project.projectbackculture.persistence.model.ImageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CloudinaryComponent {

    // Declaracion de la clase cloudinary
    private final Cloudinary cloudinary;
    private static final String UPLOAD_DIRECTORY = "images_culture";

    public CloudinaryComponent(
            @Value("${cloud.name}") String cloudName,
            @Value("${cloud.api.key}") String cloudApiKey,
            @Value("${cloud.api.secret}") String cloudApiSecret) {

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", cloudApiKey,
                "api_secret", cloudApiSecret
        ));

        log.info("Cloudinary object created");
    }

    public void uploadImage(MultipartFile file, ImageModel imageModel) throws IOException {

        //Obetener el nombre original del archivo
        String fileName = file.getOriginalFilename();

        Map<String, Object> uploadParams = new HashMap<>();
        uploadParams.put("folder", UPLOAD_DIRECTORY);
        uploadParams.put("public_id",UPLOAD_DIRECTORY+"/"+fileName);

        Map<?,?> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);
        log.info("Upload result {}", uploadResult);

        //Obtener el public_id y el url_secure
        String publicId = uploadResult.get("public_id").toString();
        String secureUrl = uploadResult.get("secure_url").toString();

        imageModel.setPublicId(publicId);
        imageModel.setSecureUrl(secureUrl);
    }

    public void deleteImage(String publicId) throws IOException {

        Map<String, Object> params = new HashMap<>();
        params.put("invalidate", true);

        cloudinary.uploader().destroy(publicId, params);
        log.info("Image with public_id {} has been deleted from Cloudinary", publicId);
    }


}
