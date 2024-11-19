package com.project.projectbackculture.service.implement;

import com.project.projectbackculture.exception.CustomException;
import com.project.projectbackculture.external.CloudinaryComponent;
import com.project.projectbackculture.mapper.ImageMapper;
import com.project.projectbackculture.persistence.model.ImageModel;
import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.persistence.repository.ImageRepository;
import com.project.projectbackculture.persistence.repository.PlaceRepository;
import com.project.projectbackculture.service.interfaces.PhotoService;
import com.project.projectbackculture.web.request.NewPhotoRequest;
import com.project.projectbackculture.web.response.PhotoResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {

    private final ImageRepository imageRepository;
    private final PlaceRepository placeRepository;
    private final CloudinaryComponent cloudinaryComponent;

    public PhotoServiceImpl(
        ImageRepository imageRepository,
        PlaceRepository placeRepository,
        CloudinaryComponent cloudinaryComponent
    ) {
        this.imageRepository = imageRepository;
        this.placeRepository = placeRepository;
        this.cloudinaryComponent = cloudinaryComponent;
    }

    @Override
    @Transactional
    public PhotoResponse saveWithImage(
        NewPhotoRequest newPhotoRequest,
        MultipartFile file,
        Integer placeId
    ) {
        log.info("Iniciando registro de foto");

        try {
            //valida los params placeId y file
            validateRequest(placeId, file);
            PlaceModel placeModel = findPlace(placeId);

            ImageModel imageModel = ImageMapper.toModel(newPhotoRequest);
            imageModel.setPlace(placeModel);
            //Carga la imagen en la base de datos
            cloudinaryComponent.uploadImage(file, imageModel);
            imageModel.setUploadDate(LocalDate.now());

            // Guarda la foto en la base datos
            ImageModel savedPhoto = imageRepository.save(imageModel);

            return ImageMapper.toResponse(savedPhoto);
        } catch (IOException e) {
            log.error("Error al registrar foto: {}", e.getMessage());
            throw new CustomException("Erro al registrar foto");
        }
    }

    @Override
    public void validateRequest(Integer placeId, MultipartFile file) {
        if (placeId == null) throw new CustomException(
            "placeId no debe ser nulo"
        );
        if (file == null) throw new CustomException("file no debe ser nulo");
    }

    @Override
    public PlaceModel findPlace(Integer placeId) {
        return placeRepository
            .findById(placeId)
            .orElseThrow(() ->
                new CustomException(
                    "Place con ID=" + placeId + " no encontrado"
                )
            );
    }

    @Override
    public PhotoResponse save(NewPhotoRequest request) {
        return null;
    }

    @Override
    public PhotoResponse update(NewPhotoRequest request, Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {}

    @Override
    public List<PhotoResponse> findAll() {
        return List.of();
    }

    @Override
    public Optional<PhotoResponse> findById(Integer integer) {
        return Optional.empty();
    }
}
