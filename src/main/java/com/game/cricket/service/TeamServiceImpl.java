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

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    Mapper mapper;

    public ResponseDTO addTeam(Team team) {
        Team newTeam = new Team(team.getName(), team.getLocation(),new Date(),new Date());
        teamRepository.save(newTeam);

        return new ResponseDTO(Constants.SUCCESS, Constants.TEAM_ADDED, newTeam);
    }

    public ResponseDTO findTeam(Long id) {
        Team team = teamRepository.findById(id).get();
        return new ResponseDTO(Constants.SUCCESS,Constants.TEAM_FOUND, team);
    }

    public ResponseDTO updateTeam(TeamDTO teamDTO, Long id) {
        Team team = teamRepository.findById(id).get();
        mapper.teamDtoToTeam(team, teamDTO);
        team.setUpdatedAt(new Date());
        teamRepository.save(team);

        return new ResponseDTO(Constants.SUCCESS, team.getName() + Constants.TEAM_UPDATED, team);
    }

    public ResponseDTO removeTeam(Long id) {
        Team team = teamRepository.findById(id).get();

        for(Player iterate : team.getPlayers()) {
            iterate.setTeam(null);
        }
        teamRepository.delete(team);
        return new ResponseDTO(Constants.SUCCESS,
                team.getName() + Constants.TEAM_REMOVED, team);
    }

    public ResponseDTO findPlayersByTeamId(Long id) {

       List<Player> players = playerRepository.findByTeamId(id);
        return new ResponseDTO(Constants.SUCCESS,
                Constants.PLAYERS_IN_TEAM + players.get(0).getTeam().getName(), players);
    }


}
