package com.game.cricket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String location;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private Set<Player> players ;

    public Team(String name, String location, Date createdAt, Date updatedAt) {
        this.name = name;
        this.location = location;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
