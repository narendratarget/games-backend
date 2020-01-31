package com.games.app.service;

import org.springframework.stereotype.Service;

import com.games.app.dto.GameRequest;
import com.games.app.dto.GameResponse;

@Service
public interface GameService {

	GameResponse newGame(GameRequest gameRequest) ;

	GameResponse removeGame(Long gameId);

	GameResponse updateGame(Long gameId, GameRequest gameRequest);

	GameResponse getGames();

	GameResponse getGame(Long gameId);
	
}
