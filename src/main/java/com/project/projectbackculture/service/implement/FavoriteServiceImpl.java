package com.project.projectbackculture.service.implement;

import com.project.projectbackculture.exception.CustomException;
import com.project.projectbackculture.mapper.FavoriteMapper;
import com.project.projectbackculture.persistence.model.FavoriteModel;
import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.persistence.model.UserModel;
import com.project.projectbackculture.persistence.repository.FavoriteRepository;
import com.project.projectbackculture.persistence.repository.PlaceRepository;
import com.project.projectbackculture.persistence.repository.UserRepository;
import com.project.projectbackculture.service.interfaces.FavoriteService;
import com.project.projectbackculture.web.request.NewFavoriteRequest;
import com.project.projectbackculture.web.response.FavoriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository,
                               UserRepository userRepository,
                               PlaceRepository placeRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    @Override
    @Transactional
    public FavoriteResponse addFavorite(Integer userId, Integer placeId) {

        //Valida los params
        validateIDRequest(userId, placeId);

        try{

            //Buscar al user y lugar por sus IDs
            UserModel userModel = findByUserId(userId);
            PlaceModel placeModel = findByPlaceId(placeId);

            //Valida si un user ya tiene guardado el favorito un lugar
            checkExistingFavorite(userModel.getUserId(), placeModel.getPlaceId());

            // Setea el user y place al modelo favorite
            FavoriteModel favoriteModel = new FavoriteModel();
            favoriteModel.setUser(userModel);
            favoriteModel.setPlace(placeModel);
            favoriteModel.setSavedDate(LocalDate.now());

            //Guardar el favorito en la BD
            FavoriteModel savedModel = favoriteRepository.save(favoriteModel);
            log.info("Saved favorite successfully");

            return FavoriteMapper.toResponse(savedModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomException("Error while adding favorite "+ e.getMessage());
        }

    }

    @Override
    public void checkExistingFavorite(Integer userId, Integer placeId) {
        boolean exists = favoriteRepository.existsByUserUserIdAndPlacePlaceId(userId, placeId);
        if(exists) throw new CustomException("The user already has favorite");
    }

    @Override
    public UserModel findByUserId(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found"));
    }

    @Override
    public PlaceModel findByPlaceId(Integer placeId) {
        return placeRepository.findById(placeId)
                .orElseThrow(() -> new CustomException("Place not found"));
    }

    @Override
    public void validateIDRequest(Integer userId, Integer placeId) {
        if(userId == null) throw new CustomException("UserId is required");
        if(placeId == null) throw new CustomException("PlaceId is required");
    }

    @Override
    public FavoriteResponse save(NewFavoriteRequest request) {
        return null;
    }

    @Override
    public FavoriteResponse update(NewFavoriteRequest request, Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<FavoriteResponse> findAll() {
        return List.of();
    }

    @Override
    public Optional<FavoriteResponse> findById(Integer integer) {
        return Optional.empty();
    }


}
