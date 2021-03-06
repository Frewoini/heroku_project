package com.mlb.mlb_api.service;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.entities.Player;

public interface PlayerService {
//    Player save(PlayerDTO playerDTO);
//    Player update(PlayerDTO playerDTO);
//    void delete(Integer playerId);
//    Iterable<Player> findAll();
//    Player findById(Integer playerId);

      Player save(PlayerDTO playerDTO);
      Player update(PlayerDTO playerDTO);

    Player update(PlayerDTO playerDTO, Integer playerId);

    void delete(Integer playerId);
      Iterable <Player> findAll();
       Player findByName(String name);
       Player findById(Integer ID);
}
