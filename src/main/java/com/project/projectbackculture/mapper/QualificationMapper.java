package com.project.projectbackculture.mapper;

import com.project.projectbackculture.controller.request.NewQualificationRequest;
import com.project.projectbackculture.controller.response.QualificationResponse;
import com.project.projectbackculture.persistence.model.QualificationModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QualificationMapper {

    QualificationMapper INSTANCE = Mappers.getMapper(QualificationMapper.class);

    QualificationModel toModel(NewQualificationRequest request);
    QualificationResponse toResponse(QualificationModel qualification);

}
