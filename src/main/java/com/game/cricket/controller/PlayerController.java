package com.game.cricket.controller;

import com.game.cricket.dto.PlayerDTO;
import com.game.cricket.dto.ResponseDTO;
import com.game.cricket.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    //API end point to add a new player
    @PostMapping(value = "/add")
    private ResponseDTO addPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.addPlayer(playerDTO);
    }

    //API end point to find an existing player
    @GetMapping(value = "/{id}")
    private ResponseDTO findPlayer(@PathVariable("id") Long id) {
        return playerService.findPlayer(id);
    }

    //API end point to update the details of an existing player
    @PutMapping(value = "/{id}")
    private ResponseDTO updatePlayer(@PathVariable("id") Long id,
                                    @RequestBody PlayerDTO playerDTO) {
        return playerService.updatePlayer(playerDTO, id);
    }

    //API end point to remove the player
    @DeleteMapping(value = "/{id}")
    private ResponseDTO removePlayer(@PathVariable("id") Long id) {
        return playerService.removePlayer(id);
    }

    //API end point to find the team of a player by player id
    @GetMapping(value = "team/{teamId}")
    private ResponseDTO findTeamByPlayerId(@PathVariable("teamId") Long teamId){
        return playerService.findTeamByPlayerId(teamId);
    }
}
