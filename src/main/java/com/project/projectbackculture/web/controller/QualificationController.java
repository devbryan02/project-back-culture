package com.project.projectbackculture.web.controller;

import com.project.projectbackculture.service.implement.QualificationServiceImpl;
import com.project.projectbackculture.web.request.NewQualificationRequest;
import com.project.projectbackculture.web.response.QualificationResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/qualification")
public class QualificationController {

    private final QualificationServiceImpl qualificationService;

    public QualificationController(QualificationServiceImpl qualificationService) {
        this.qualificationService = qualificationService;
    }

    @PostMapping
    public ResponseEntity<QualificationResponse> createQualifaction(
            @RequestBody @Valid NewQualificationRequest request){
        QualificationResponse qualificationResponse = qualificationService.save(request);
        return new ResponseEntity<>(qualificationResponse, HttpStatus.CREATED);
    }
}
