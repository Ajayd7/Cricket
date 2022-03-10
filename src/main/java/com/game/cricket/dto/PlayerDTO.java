package com.game.cricket.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.cricket.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerDTO {

    private String name;
    private int age;
    private String playerType;
    private String teamName;
    private Long teamId;
    
}
