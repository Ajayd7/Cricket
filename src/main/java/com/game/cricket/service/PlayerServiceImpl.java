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
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    Mapper mapper;


    //To add a new player
    public ResponseDTO addPlayer(PlayerDTO playerDTO) {
        //Finding the team with the entered team id
        Optional<Team> team = teamRepository.findById(playerDTO.getTeamId());

        //If team doesn't exist
        if(team.isEmpty())
            return new ResponseDTO(Constants.FAILURE,
                    Constants.TEAM_NOT_EXIST + playerDTO.getTeamId(), playerDTO);

        //If team exists
        //Saving the details of player to their respective attributes
        Player newPlayer = new Player(
                playerDTO.getName(),playerDTO.getAge(),playerDTO.getPlayerType(),team.get(),new Date(),new Date());
        playerRepository.save(newPlayer);

        //Converting the player entity into playerdto through the user defined mapper
        //To send the added player details in response data
        PlayerDTO addPlayerDTO = new PlayerDTO();
        mapper.playerToPlayerDto(addPlayerDTO, newPlayer);
        return new ResponseDTO(Constants.SUCCESS,
                playerDTO.getName() + Constants.PLAYER_ADDED + team.get().getName(), addPlayerDTO);
    }

    //To find an existing player
    public ResponseDTO findPlayer(Long id) {
        //Finding the player with the entered player id
        Optional<Player> player = playerRepository.findById(id);

        //If player doesn't exist
        if(player.isEmpty())
            return new ResponseDTO(Constants.FAILURE,
                    Constants.PLAYER_NOT_EXIST + id, null);

        //If player exists
        return new ResponseDTO(Constants.SUCCESS,Constants.PLAYER_FOUND, player);
    }

    //To update the details of an existing player
    public ResponseDTO updatePlayer(PlayerDTO playerDTO, Long id) {
        //Finding the player with the entered player id
        Optional<Player> existingPlayer = playerRepository.findById(id);

        //If player doesn't exist
        if(existingPlayer.isEmpty())
            return new ResponseDTO(Constants.FAILURE,
                    Constants.PLAYER_NOT_EXIST + id, playerDTO);

        //If player exist
        //If player wants to change the team
        if (playerDTO.getTeamId() != null) {

            //Finding the team with the entered team id
            Optional<Team> team = teamRepository.findById(playerDTO.getTeamId());

            //If team doesn't exist
            if (team.isEmpty())
                return new ResponseDTO(Constants.FAILURE,
                        Constants.TEAM_NOT_EXIST + playerDTO.getTeamId(), playerDTO);

            //If team exists
            existingPlayer.get().setTeam(team.get());
        }

        //Converting the playerdto to player entity through the user defined mapper
        //Updating the details of the player
        mapper.playerDtoToPlayer(existingPlayer.get(), playerDTO);
        existingPlayer.get().setUpdatedAt(new Date());
        playerRepository.save(existingPlayer.get());

        //Converting the player Entity into playerdto to send the updated player details it in response data
        mapper.playerToPlayerDto(playerDTO, existingPlayer.get());
        return new ResponseDTO(Constants.SUCCESS,
                existingPlayer.get().getName() + Constants.PLAYER_UPDATED , playerDTO);
    }

    //To remove the player
    public ResponseDTO removePlayer(Long id) {
        //Finding the player with the entered player id
        Optional<Player> player = playerRepository.findById(id);

        //If player doesn't exist
        if(player.isEmpty())
            return new ResponseDTO(Constants.FAILURE,
                    Constants.PLAYER_NOT_EXIST + id, null);

        //If player exists
        //Remove the player from table
        playerRepository.delete(player.get());

        //Converting the player Entity into playerdto to send the removed player details in response data
        PlayerDTO playerDTO = new PlayerDTO();
        mapper.playerToPlayerDto(playerDTO, player.get());
        return new ResponseDTO(Constants.SUCCESS,
                player.get().getName() + Constants.PLAYER_REMOVED, playerDTO);
    }

    //To find the team of a player by player id
    public ResponseDTO findTeamByPlayerId(Long id) {
        //Finding the player with the entered player id
        Optional<Player> player = playerRepository.findById(id);

        //If player doesn't exist
        if(player.isEmpty())
            return new ResponseDTO(Constants.FAILURE,
                    Constants.PLAYER_NOT_EXIST + id, null);

        //If player exists
        //If player is not associated with any team
        if(player.get().getTeam() == null)
            return new ResponseDTO(Constants.SUCCESS,
                    player.get().getName() + Constants.PLAYER_ASSOCIATED_WITH_NO_TEAM, player);

        //Converting the team into teamdto to send the team details of player in response data
        TeamDTO teamDTO = new TeamDTO();
        mapper.teamTOTeamDto(teamDTO, player.get().getTeam());
        return new ResponseDTO(Constants.SUCCESS,
                player.get().getName() + Constants.PLAYER_ASSOCIATED_WITH_TEAM + teamDTO.getName(), teamDTO);
    }
}
