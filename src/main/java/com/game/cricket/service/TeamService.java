package com.game.cricket.service;

import com.game.cricket.dto.ResponseDTO;
import com.game.cricket.dto.TeamDTO;
import com.game.cricket.model.Team;
import org.springframework.stereotype.Component;

@Component
public interface TeamService {

     ResponseDTO addTeam(Team team);

     ResponseDTO findTeam(Long id);

     ResponseDTO updateTeam(TeamDTO teamDTO, Long id);

     ResponseDTO removeTeam(Long id);

     ResponseDTO findPlayersByTeamId(Long teamId);
}
