package com.game.cricket.service;

import com.game.cricket.dto.PlayerDTO;
import com.game.cricket.dto.ResponseDTO;
import com.game.cricket.dto.TeamDTO;
import com.game.cricket.model.Player;
import com.game.cricket.model.Team;
import com.game.cricket.repository.PlayerRepository;
import com.game.cricket.repository.TeamRepository;
import com.game.cricket.util.Constants;
import com.game.cricket.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    Mapper mapper;


    public ResponseDTO addPlayer(PlayerDTO playerDTO) {
        Team team = teamRepository.findById(playerDTO.getTeamId()).get();
        Player newPlayer = new Player(
                playerDTO.getName(),playerDTO.getAge(),playerDTO.getPlayerType(),team,new Date(),new Date());
        playerRepository.save(newPlayer);
        PlayerDTO addPlayerDTO = new PlayerDTO();
        mapper.playerToPlayerDto(addPlayerDTO, newPlayer);
        return new ResponseDTO(Constants.SUCCESS,
                playerDTO.getName() + Constants.PLAYER_ADDED + team.getName(), addPlayerDTO);
    }

    public ResponseDTO findPlayer(Long id) {
        Player player = playerRepository.findById(id).get();
        return new ResponseDTO(Constants.SUCCESS,Constants.PLAYER_FOUND, player);
    }

    public ResponseDTO updatePlayer(PlayerDTO playerDTO, Long id) {
        Player existingPlayer = playerRepository.findById(id).get();
        if (playerDTO.getTeamId() != null)
             existingPlayer.setTeam(teamRepository.findById(playerDTO.getTeamId()).get());
        mapper.playerDtoToPlayer(existingPlayer, playerDTO);
        existingPlayer.setUpdatedAt(new Date());
        playerRepository.save(existingPlayer);
        mapper.playerToPlayerDto(playerDTO, existingPlayer);
        return new ResponseDTO(Constants.SUCCESS,
                existingPlayer.getName() + Constants.PLAYER_UPDATED , playerDTO);
    }

    public ResponseDTO removePlayer(Long id) {
        Player player = playerRepository.findById(id).get();
        playerRepository.delete(player);
        PlayerDTO playerDTO = new PlayerDTO();
        mapper.playerToPlayerDto(playerDTO, player);
        return new ResponseDTO(Constants.SUCCESS,
                player.getName() + Constants.PLAYER_REMOVED, playerDTO);
    }

    public ResponseDTO findTeamByPlayerId(Long id) {
        Player player = playerRepository.findById(id).get();
        TeamDTO teamDTO = new TeamDTO();
        mapper.teamTOTeamDto(teamDTO, player.getTeam());
        return new ResponseDTO(Constants.SUCCESS,
                player.getName() + Constants.PLAYER_ASSOCIATED_WITH_TEAM + teamDTO.getName(), teamDTO);
    }
}
