package com.games.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.games.app.model.Game;

@Repository
//@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {

	Game findGameById(Long gameId);
}
