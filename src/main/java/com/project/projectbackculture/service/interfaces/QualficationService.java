package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.persistence.model.UserModel;
import com.project.projectbackculture.web.request.NewQualificationRequest;
import com.project.projectbackculture.web.response.QualificationResponse;

public interface QualficationService extends ServiceGeneral<
        QualificationResponse,
        NewQualificationRequest,
        Integer>{

    // methods
    void checkExistingQualification(Integer userId, Integer placeId);

    UserModel findUser(Integer userId);
    PlaceModel findPlace(Integer placeId);

}
