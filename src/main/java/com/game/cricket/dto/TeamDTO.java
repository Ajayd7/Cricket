package com.game.cricket.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class TeamDTO {

    private Long id;
    private String name;
    private String location;
    private Date createdAt;
    private Date updatedAt;
}
