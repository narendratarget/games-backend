package com.games.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.games.app.constants.ConstantURL;
import com.games.app.dto.GameRequest;
import com.games.app.dto.GameResponse;
import com.games.app.service.GameService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ConstantURL.BASIC_PATH)
public class MainController {

	@Autowired
	GameService gameService;
	
	@ApiOperation(value = "API to save game record", response = GameResponse.class)
	@RequestMapping(value = ConstantURL.GAME_URL, method = RequestMethod.POST, produces = "application/json")
	@CrossOrigin
	public GameResponse newGame(@RequestBody GameRequest gameRequest) throws Exception {

		return gameService.newGame(gameRequest);
	}

	@ApiOperation(value = "API to delete game record", response = GameResponse.class)
	@RequestMapping(value = ConstantURL.GAME_URL, method = RequestMethod.DELETE, produces = "application/json")
	@CrossOrigin
	public GameResponse removeGame(@RequestParam Long gameId) throws Exception {

		return gameService.removeGame(gameId);
	}
	
	@ApiOperation(value = "API to update game record", response = GameResponse.class)
	@RequestMapping(value = ConstantURL.GAME_URL, method = RequestMethod.PUT, produces = "application/json")
	@CrossOrigin
	public GameResponse updateGame(@RequestParam Long gameId,@RequestBody GameRequest gameRequest) throws Exception {
		return gameService.updateGame(gameId,gameRequest);
	}
	
	@ApiOperation(value = "API to get all game record", response = GameResponse.class)
	@RequestMapping(value = ConstantURL.GAME_ALL_URL, method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin
	public GameResponse getAllGame() throws Exception {
		GameResponse res=gameService.getGames();
		
		return res;
	}
	
	@ApiOperation(value = "API to get game record by id", response = GameResponse.class)
	@RequestMapping(value = ConstantURL.GAME_URL, method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin
	public GameResponse getGame(@RequestParam Long gameId) throws Exception {
		return gameService.getGame(gameId);
	}
	
	
}
