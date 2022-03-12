package com.game.cricket.service;

import com.game.cricket.dto.PlayerDTO;
import com.game.cricket.dto.ResponseDTO;
import org.springframework.stereotype.Component;

@Component
public interface PlayerService {

     ResponseDTO addPlayer(PlayerDTO playerDTO);

     ResponseDTO findPlayer(Long id);

     ResponseDTO updatePlayer(PlayerDTO playerDTO, Long id);

     ResponseDTO removePlayer(Long id);

     ResponseDTO findTeamByPlayerId(Long id);

}
