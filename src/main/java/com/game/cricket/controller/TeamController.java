package com.game.cricket.controller;

import com.game.cricket.dto.ResponseDTO;
import com.game.cricket.dto.TeamDTO;
import com.game.cricket.model.Team;
import com.game.cricket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    //API end point to add a new team
    @PostMapping(value = "/add")
    private ResponseDTO addTeam(@RequestBody Team team) {
        return teamService.addTeam(team);
    }

    //API end point to find an existing team
    @GetMapping(value = "/{id}")
    private ResponseDTO findTeam(@PathVariable("id") Long id) {
        return teamService.findTeam(id);
    }

    //API end point to update details of an existing team
    @PutMapping(value = "/{id}")
    private ResponseDTO updateTeam(@PathVariable("id") Long id, @RequestBody TeamDTO teamDTO) {
        return teamService.updateTeam(teamDTO, id);
    }

    //API end point to remove an existing team
    @DeleteMapping(value = "/{id}")
    private ResponseDTO removeTeam(@PathVariable("id") Long id) {
        return teamService.removeTeam(id);
    }

    //API end point to find all the players of an existing team
    @GetMapping(value = "/players/{teamId}")
    private ResponseDTO findPlayersByTeamId(@PathVariable("teamId") Long id) {
        return teamService.findPlayersByTeamId(id);
    }
}
