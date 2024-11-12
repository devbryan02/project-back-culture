package com.project.projectbackculture.mapper;

import com.project.projectbackculture.persistence.model.QualificationModel;
import com.project.projectbackculture.web.request.NewQualificationRequest;
import com.project.projectbackculture.web.response.QualificationResponse;

public class QualificationMapper {

    // Mapear modelo a response
    public static QualificationResponse toResponse(QualificationModel qualificationModel) {

        if(qualificationModel == null) return null;

        return QualificationResponse.builder()
                .qualificationId(qualificationModel.getQualificationId())
                .userId(qualificationModel.getUser().getUserId())
                .placeId(qualificationModel.getPlace().getPlaceId())
                .punctuation(qualificationModel.getPunctuation())
                .build();
    }

    //Mapear de request a model
    public static QualificationModel toModel(NewQualificationRequest request) {

        if(request == null) return null;

        return QualificationModel.builder()
                .punctuation(request.punctuation())
                .build();
    }

}
