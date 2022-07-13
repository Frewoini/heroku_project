package com.mlb.mlb_api.controllers;


import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.entities.Player;
import com.mlb.mlb_api.repositories.PlayerRepository;
import com.mlb.mlb_api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
  private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/add")
    public Player createPlayer(@RequestBody PlayerDTO newPlayerDTO){

        return playerService.save(newPlayerDTO);
    }
    @GetMapping("/all")
    public Iterable<Player> getAllPlayer(){

        return playerService.findAll();
    }
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Integer playerId){
       return playerService.findById(playerId);
   }
   @PutMapping("/update/{id}")
    public Player updatedPlayer(@PathVariable("id") Integer playerId,@RequestBody PlayerDTO playerDTO ){
   return playerService.update(playerDTO,playerId);

       }
       @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") Integer playerId){
        playerService.delete(playerId);
       }
    @GetMapping("/search")
   public Player searchPlayer(@RequestParam( required = false) String name) {

         return this.playerService.findByName(name);

      }
   }

