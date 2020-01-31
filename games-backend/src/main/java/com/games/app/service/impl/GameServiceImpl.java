package com.games.app.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.games.app.constants.ExceptionConstant;
import com.games.app.constants.StatusConstant;
import com.games.app.dto.GameObj;
import com.games.app.dto.GameRequest;
import com.games.app.dto.GameResponse;
import com.games.app.model.Game;
import com.games.app.repository.GameRepository;
import com.games.app.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameRepository gameRepository;

	@Override
	public GameResponse newGame(GameRequest gameRequest) {
		GameResponse response = new GameResponse();
		if (gameRequest != null) {
			Game game = new Game();
			game.setTitle(gameRequest.getTitle());
			game.setPlatform(gameRequest.getPlatform());
			game.setScore(gameRequest.getScore());
			game.setGenre(gameRequest.getGenre());
			game.setEditors_choice(gameRequest.getEditors_choice());

			try {

				response.setId(gameRepository.save(game).getId().toString());
				response.setStatus(StatusConstant.STATUS_SUCCESS);

			} catch (Throwable th) {

				response.setStatus(StatusConstant.STATUS_FAILURE);
				response.setErrorCode(ExceptionConstant.UNKNOWN_ERROR_EC);
				response.setErrorDesc(ExceptionConstant.UNKNOWN_ERROR_ED);

			}
		} else {

			response.setStatus(StatusConstant.STATUS_FAILURE);
			response.setErrorCode(ExceptionConstant.INVALID_REQUEST_EC);
			response.setErrorDesc(ExceptionConstant.INVALID_REQUEST_ED);
		}
		return response;
	}

	@Override
	public GameResponse removeGame(Long gameId) {
		GameResponse response = new GameResponse();
		try {
			gameRepository.delete(gameRepository.findGameById(gameId));
			response.setStatus(StatusConstant.STATUS_SUCCESS);
			response.setId(gameId.toString());
		} catch (Throwable th) {

			response.setStatus(StatusConstant.STATUS_FAILURE);
			response.setErrorCode(ExceptionConstant.UNKNOWN_ERROR_EC);
			response.setErrorDesc(ExceptionConstant.UNKNOWN_ERROR_ED);

		}
		return response;
	}

	@Override
	public GameResponse updateGame(Long gameId, GameRequest gameRequest) {
		GameResponse response = new GameResponse();
		try {
			Game game = gameRepository.findGameById(gameId);
			if (game != null) {
				game.setTitle(gameRequest.getTitle());
				game.setPlatform(gameRequest.getPlatform());
				game.setScore(gameRequest.getScore());
				game.setGenre(gameRequest.getGenre());
				game.setEditors_choice(gameRequest.getEditors_choice());

				gameRepository.save(gameRepository.findGameById(gameId));
				response.setStatus(StatusConstant.STATUS_SUCCESS);
				response.setId(game.getId().toString());
			} else {
				response.setStatus(StatusConstant.STATUS_FAILURE);
				response.setErrorCode(ExceptionConstant.INVALID_GAME_ID_EC);
				response.setErrorDesc(ExceptionConstant.INVALID_GAME_ID_ED);

			}
		} catch (Throwable th) {

			response.setStatus(StatusConstant.STATUS_FAILURE);
			response.setErrorCode(ExceptionConstant.UNKNOWN_ERROR_EC);
			response.setErrorDesc(ExceptionConstant.UNKNOWN_ERROR_ED);

		}
		return response;
	}

	@Override
	public GameResponse getGames() {
		GameResponse response = new GameResponse();
		try {
			List<Game> gameList = gameRepository.findAll();

			if (gameList.size() > 0) {

				List list=gameList
						.stream().map((game) -> new GameObj(game.getId().toString(), game.getTitle(), game.getPlatform(),
								game.getScore(), game.getGenre(), game.getEditors_choice()))
						.collect(Collectors.toList());
				//System.out.println(list.toString());
				response.setList(list);
				response.setStatus(StatusConstant.STATUS_SUCCESS);

			} else {

				response.setStatus(StatusConstant.STATUS_FAILURE);
				response.setErrorCode(ExceptionConstant.DATA_NOT_FOUND_EC);
				response.setErrorDesc(ExceptionConstant.DATA_NOT_FOUND_ED);
			}
		} catch (Throwable th) {
			response.setStatus(StatusConstant.STATUS_FAILURE);
			response.setErrorCode(ExceptionConstant.UNKNOWN_ERROR_EC);
			response.setErrorDesc(ExceptionConstant.UNKNOWN_ERROR_ED);
		}
			
		return response;
	}

	@Override
	public GameResponse getGame(Long gameId) {
		GameResponse response = new GameResponse();
		try {
			List<Game> gameList = Arrays.asList(gameRepository.findGameById(gameId));

			if (gameList.size() > 0) {

				response.setList(gameList.stream().map((game) -> new GameObj(game.getId().toString(),game.getTitle(), game.getPlatform(),
						game.getScore(), game.getGenre(), game.getEditors_choice())).collect(Collectors.toList()));
				response.setStatus(StatusConstant.STATUS_SUCCESS);

			} else {

				response.setStatus(StatusConstant.STATUS_FAILURE);
				response.setErrorCode(ExceptionConstant.DATA_NOT_FOUND_EC);
				response.setErrorDesc(ExceptionConstant.DATA_NOT_FOUND_ED);
			}
		} catch (Throwable th) {
			response.setStatus(StatusConstant.STATUS_FAILURE);
			response.setErrorCode(ExceptionConstant.UNKNOWN_ERROR_EC);
			response.setErrorDesc(th.getMessage());
			th.printStackTrace();
		}
		return response;
	}

}
