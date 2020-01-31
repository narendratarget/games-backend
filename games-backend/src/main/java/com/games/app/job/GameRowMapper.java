package com.games.app.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.games.app.dto.GameRequest;
import com.games.app.model.Game;

public class GameRowMapper implements FieldSetMapper<GameRequest> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameRowMapper.class);

	@Override
	public GameRequest mapFieldSet(FieldSet fieldSet) throws BindException {
		GameRequest game = new GameRequest();
		try {
			game.setTitle(fieldSet.readString("title"));
			game.setPlatform(fieldSet.readString("platform"));
			game.setScore(fieldSet.readString("score"));
			game.setGenre(fieldSet.readString("genre"));
			game.setEditors_choice(fieldSet.readString("editors_choice"));
			
		} catch (Throwable th) {
			LOGGER.error(th.getMessage());
		}
		return game;
	}

}
