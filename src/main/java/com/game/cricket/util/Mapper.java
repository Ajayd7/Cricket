package com.game.cricket.util;

import com.game.cricket.dto.PlayerDTO;
import com.game.cricket.dto.TeamDTO;
import com.game.cricket.model.Player;
import com.game.cricket.model.Team;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface Mapper {

        Mapper INSTANCE = Mappers.getMapper(Mapper.class);

        void playerDtoToPlayer(@MappingTarget Player player, PlayerDTO playerDTO);

        @Mapping(target = "teamName", source = "team.name")
        @Mapping(target = "teamId", source = "team.id")
        void playerToPlayerDto(@MappingTarget PlayerDTO playerDTO, Player player);

        void teamTOTeamDto(@MappingTarget TeamDTO teamDTO, Team team);

        void teamDtoToTeam(@MappingTarget Team team, TeamDTO teamDTO);




}
