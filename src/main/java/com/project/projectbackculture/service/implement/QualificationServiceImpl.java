package com.project.projectbackculture.service.implement;

import com.project.projectbackculture.exception.CustomException;
import com.project.projectbackculture.mapper.QualificationMapper;
import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.persistence.model.QualificationModel;
import com.project.projectbackculture.persistence.model.UserModel;
import com.project.projectbackculture.persistence.repository.PlaceRepository;
import com.project.projectbackculture.persistence.repository.QualificationRepository;
import com.project.projectbackculture.persistence.repository.UserRepository;
import com.project.projectbackculture.service.interfaces.QualficationService;
import com.project.projectbackculture.web.request.NewQualificationRequest;
import com.project.projectbackculture.web.response.QualificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QualificationServiceImpl implements QualficationService {

    private final QualificationRepository qualificationRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public QualificationServiceImpl(QualificationRepository qualificationRepository,
                                    UserRepository userRepository,
                                    PlaceRepository placeRepository) {
        this.qualificationRepository = qualificationRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    @Override
    public QualificationResponse save(NewQualificationRequest request) {

        log.info("Saving new qualification {}", request);

        if(request == null) throw new CustomException("Request cannot be null");

        UserModel userModel = findUser(request.userId());
        PlaceModel placeModel = findPlace(request.placeId());

        // Vefifica si el usuario ya calificó este lugar
        checkExistingQualification(userModel.getUserId(), placeModel.getPlaceId());

        QualificationModel qualificationModel = QualificationMapper.toModel(request);
        //Asignar el usuario y lugar
        qualificationModel.setUser(userModel);
        qualificationModel.setPlace(placeModel);

        QualificationModel savedQualificationModel = qualificationRepository.save(qualificationModel);
        log.info("Saved qualification {}", savedQualificationModel);

        return QualificationMapper.toResponse(savedQualificationModel);
    }

    @Override
    public QualificationResponse update(NewQualificationRequest request, Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<QualificationResponse> findAll() {
        return List.of();
    }

    @Override
    public Optional<QualificationResponse> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public void checkExistingQualification(Integer userId, Integer placeId) {
        boolean exists = qualificationRepository.existsByUserUserIdAndPlacePlaceId(userId, placeId);
        if (exists) throw new CustomException("The user has already rated this place");
    }

    @Override
    public UserModel findUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found"));
    }

    @Override
    public PlaceModel findPlace(Integer placeId) {
        return placeRepository.findById(placeId)
                .orElseThrow(() -> new CustomException("Place not found"));
    }
}
