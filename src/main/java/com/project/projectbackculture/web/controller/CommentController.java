package com.project.projectbackculture.web.controller;

import com.project.projectbackculture.service.implement.CommentServiceImpl;
import com.project.projectbackculture.web.request.NewCommentRequest;
import com.project.projectbackculture.web.response.CommentByPlaceResponse;
import com.project.projectbackculture.web.response.CommentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "comment")
public class CommentController {

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CommentResponse> createComment(
            @RequestBody @Valid NewCommentRequest request,
            @RequestParam Integer userId,
            @RequestParam Integer placeId){
        CommentResponse  commentResponse = commentService.addComment(request, placeId, userId);
        return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/place")
    public ResponseEntity<List<CommentByPlaceResponse>> findCommentsByPlaceId(
            @RequestParam Integer placeId){
        List<CommentByPlaceResponse> findComments = commentService.findCommentByPlace(placeId);
        return new ResponseEntity<>(findComments, HttpStatus.OK);
    }
}
