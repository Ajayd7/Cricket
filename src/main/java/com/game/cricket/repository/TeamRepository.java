package com.game.cricket.repository;

import com.game.cricket.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TeamRepository extends CrudRepository<Team, Serializable> {

}
