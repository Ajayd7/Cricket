package com.game.cricket.repository;

import com.game.cricket.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Serializable> {

     List<Player> findByTeamId(Long id);

}
