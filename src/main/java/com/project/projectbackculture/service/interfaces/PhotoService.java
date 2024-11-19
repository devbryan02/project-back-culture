package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.web.request.NewPhotoRequest;
import com.project.projectbackculture.web.response.PhotoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService extends ServiceGeneral<PhotoResponse, NewPhotoRequest,Integer>{

    // no hay mas metodos para photo model
    PhotoResponse saveWithImage(NewPhotoRequest newPhotoRequest, MultipartFile file, Integer placeId);
    void validateRequest(Integer placeId, MultipartFile file);
    PlaceModel findPlace(Integer placeId);
}
