package com.game.cricket.service;

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
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    Mapper mapper;

    //To add a new team
    public ResponseDTO addTeam(Team team) {
        //Saving the details of team
        Team newTeam = new Team(team.getName(), team.getLocation(),new Date(),new Date());
        teamRepository.save(newTeam);
        return new ResponseDTO(Constants.SUCCESS, Constants.TEAM_ADDED, newTeam);
    }

    //To find the existing team
    public ResponseDTO findTeam(Long id) {
        //Finding the team with the entered team id
        Optional<Team> team = teamRepository.findById(id);

        //If team doesn't exist
        if(team.isEmpty())
            return new ResponseDTO(Constants.FAILURE,
                    Constants.TEAM_NOT_EXIST + id, null);

        //If team exists
        return new ResponseDTO(Constants.SUCCESS,Constants.TEAM_FOUND, team);
    }

    //To update the details of existing team
    public ResponseDTO updateTeam(TeamDTO teamDTO, Long id) {
        //Finding the team with the entered team id
        Optional<Team> team = teamRepository.findById(id);

        //If team doesn't exist
        if(team.isEmpty())
            return new ResponseDTO(Constants.FAILURE,
                    Constants.TEAM_NOT_EXIST + id, null);

        //If team exists
        //Converting the teamdto to team entity to update the details
        mapper.teamDtoToTeam(team.get(), teamDTO);
        team.get().setUpdatedAt(new Date());
        teamRepository.save(team.get());
        return new ResponseDTO(Constants.SUCCESS, team.get().getName() + Constants.TEAM_UPDATED, team);
    }

    //To remove the team
    public ResponseDTO removeTeam(Long id) {
        //Finding the team with the entered team id
        Optional<Team> team = teamRepository.findById(id);

        //If team doesn't exist
        if(team.isEmpty())
            return new ResponseDTO(Constants.FAILURE,
                    Constants.TEAM_NOT_EXIST + id, null);

        //If team exists
        //Setting the value of team to null in player details before removing the team
        for(Player iterate : team.get().getPlayers()) {
            iterate.setTeam(null);
        }
        teamRepository.delete(team.get());
        return new ResponseDTO(Constants.SUCCESS,
                team.get().getName() + Constants.TEAM_REMOVED, team);
    }

    //To find players by team id
    public ResponseDTO findPlayersByTeamId(Long id) {
        //Finding the team with the entered team id
        Optional<Team> team = teamRepository.findById(id);

        //If team doesn't exist
        if(team.isEmpty())
            return new ResponseDTO(Constants.FAILURE,
                    Constants.TEAM_NOT_EXIST + id, null);

        //If team exists
        //Finding players if exists in team
        List<Player> players = playerRepository.findByTeamId(id);
        return new ResponseDTO(Constants.SUCCESS,
                Constants.PLAYERS_IN_TEAM + players.get(0).getTeam().getName(), players);
    }
}
