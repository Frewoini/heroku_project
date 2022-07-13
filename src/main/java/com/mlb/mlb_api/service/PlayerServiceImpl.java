package com.mlb.mlb_api.service;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.PlayerRepository;
import com.mlb.mlb_api.repositories.entities.Player;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{
    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {

        this.playerRepository = playerRepository;
    }
    @Override
    public Player save(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO);
        return this.playerRepository.save(player);
    }

    @Override
    public Player update(PlayerDTO playerDTO) {
        return null;
    }

    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findByName(String name) {
        return  this.playerRepository.findByName(name);
    }

    @Override
    public Player findById(Integer playerId) {

        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if(optionalPlayer.isEmpty()){
            return null;
        }
     Player player = optionalPlayer.get();
      return player;
    }

    @Override
    public Player update(PlayerDTO playerDTO, Integer playerId) {
        Optional<Player> optionalPlayerToUpdate = playerRepository.findById(playerId);
      if(optionalPlayerToUpdate.isEmpty()){
          return null;
       }
      Player playerToUpdateFromDB = optionalPlayerToUpdate.get();
//       //update the players information

       if(playerDTO.getName() == null){
           playerToUpdateFromDB.setName(playerToUpdateFromDB.getName());
       } else if(playerDTO.getName().isEmpty()) {
           playerToUpdateFromDB.setName(playerToUpdateFromDB.getName());
       } else {
           playerToUpdateFromDB.setName(playerDTO.getName());
       }


     playerToUpdateFromDB.setAge(playerDTO.getAge() != null ? playerDTO.getAge() : playerToUpdateFromDB.getAge());
     playerToUpdateFromDB.setRating(playerDTO.getRating() != null ? playerDTO.getRating() : playerToUpdateFromDB.getRating());
      playerToUpdateFromDB.setYearsOfExperience(playerDTO.getYearsOfExperience() != null ? playerDTO.getYearsOfExperience() : playerToUpdateFromDB.getYearsOfExperience());
//        save the player back to the DB
//        return the player to the client
        return playerRepository.save(playerToUpdateFromDB);

    }

    @Override
    public void delete(Integer playerId) {
        Optional<Player> playerToDeleteOptional = this.playerRepository.findById(playerId);
     if(playerToDeleteOptional.isEmpty()){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The player is not found.");
     }
     Player playerDeleted = playerToDeleteOptional.get();
       this.playerRepository.delete(playerDeleted);
       throw new ResponseStatusException(HttpStatus.OK,"The player deleted.");
    }


}
