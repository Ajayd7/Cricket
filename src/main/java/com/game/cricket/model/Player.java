package com.game.cricket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class Player  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String playerType;

    @JsonBackReference
    @ManyToOne
    @JoinColumn
    private Team team;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    public Player(String name, int age, String playerType, Team team, Date createdAt, Date updatedAt) {
        this.name = name;
        this.age = age;
        this.playerType = playerType;
        this.team = team;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
