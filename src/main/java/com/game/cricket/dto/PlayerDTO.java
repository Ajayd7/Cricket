package com.game.cricket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerDTO {

    private Long id;
    private String name;
    private int age;
    private String playerType;
    private String teamName;
    private Long teamId;
    
}
