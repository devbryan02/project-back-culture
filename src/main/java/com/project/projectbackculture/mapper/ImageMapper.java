package com.project.projectbackculture.mapper;

import com.project.projectbackculture.persistence.model.ImageModel;
import com.project.projectbackculture.web.request.NewPhotoRequest;
import com.project.projectbackculture.web.response.PhotoResponse;

public class ImageMapper {

    // Mapea model a response
    public static PhotoResponse toResponse(ImageModel imageModel) {

        if(imageModel == null) return null;

        return PhotoResponse.builder()
                .photoId(imageModel.getImageId())
                .placeId(imageModel.getPlace().getPlaceId())
                .pathPhoto(imageModel.getSecureUrl())
                .description(imageModel.getDescription())
                .build();
    }

    // Mapea request a model
    public static ImageModel toModel(NewPhotoRequest request) {

        if(request == null) return null;

        return ImageModel.builder()
                .description(request.description())
                .build();
    }
}
