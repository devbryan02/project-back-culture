package com.project.projectbackculture.service.implement;

import com.project.projectbackculture.exception.CustomException;
import com.project.projectbackculture.mapper.CommentMapper;
import com.project.projectbackculture.persistence.model.CommentModel;
import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.persistence.model.UserModel;
import com.project.projectbackculture.persistence.repository.CommentRepository;
import com.project.projectbackculture.persistence.repository.PlaceRepository;
import com.project.projectbackculture.persistence.repository.UserRepository;
import com.project.projectbackculture.service.interfaces.ComentService;
import com.project.projectbackculture.web.request.NewCommentRequest;
import com.project.projectbackculture.web.response.CommentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentServiceImpl implements ComentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              UserRepository userRepository,
                              PlaceRepository placeRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    @Override
    @Transactional
    public CommentResponse addComment(NewCommentRequest request, Integer userId, Integer placeId) {

        //validadmos el request de IDs
        validateIdsRequest(userId, placeId);

        try {

            //Busca el usuario y lugar por su ID
            UserModel usermodel = findUserById(userId);
            PlaceModel placeModel = findPlaceById(placeId);

            // Convierte el request en modelo
            CommentModel commentModel = CommentMapper.toModel(request);
            commentModel.setUser(usermodel);
            commentModel.setPlace(placeModel);

            //Registra en la base de datos
            CommentModel savedComment = commentRepository.save(commentModel);

            return CommentMapper.toResponse(savedComment);

        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomException("Error while adding comment");
        }
    }

    @Override
    public void validateIdsRequest(Integer userId, Integer placeId) {
        if(userId == null) throw new CustomException("UserId is null");
        if(placeId == null) throw new CustomException("PlaceId is null");
    }

    @Override
    public PlaceModel findPlaceById(Integer placeId) {
        return placeRepository.findById(placeId)
                .orElseThrow(() -> new CustomException("Place not found"));
    }

    @Override
    public UserModel findUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found"));
    }

    @Override
    public CommentResponse save(NewCommentRequest request) {
        return null;
    }

    @Override
    public CommentResponse update(NewCommentRequest request, Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<CommentResponse> findAll() {
        return List.of();
    }

    @Override
    public Optional<CommentResponse> findById(Integer integer) {
        return Optional.empty();
    }


}
