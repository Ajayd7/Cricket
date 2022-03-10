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

    @PostMapping(value = "/add")
    private ResponseDTO addPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.addPlayer(playerDTO);
    }

    @GetMapping(value = "/{id}")
    private ResponseDTO findPlayer(@PathVariable("id") Long id) {
        return playerService.findPlayer(id);
    }

    @PutMapping(value = "/{id}")
    private ResponseDTO updatePlayer(@PathVariable("id") Long id,
                                    @RequestBody PlayerDTO playerDTO) {
        return playerService.updatePlayer(playerDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseDTO removePlayer(@PathVariable("id") Long id) {
        return playerService.removePlayer(id);
    }

    @GetMapping(value = "team/{teamId}")
    private ResponseDTO findTeamByPlayerId(@PathVariable("teamId") Long teamId){
        return playerService.findTeamByPlayerId(teamId);
    }
}
