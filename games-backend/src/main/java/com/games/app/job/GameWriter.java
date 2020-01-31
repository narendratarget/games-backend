package com.games.app.job;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.games.app.model.Game;
import com.games.app.repository.GameRepository;

@Component
public class GameWriter implements ItemWriter<Game> {

	@Autowired
	GameRepository gameRepository;
	
    @Override
    public void write(List<? extends Game> gameList) throws Exception {

        try {

        	gameList.forEach((game) -> {

        		gameRepository.saveAndFlush(game);
            });
        } catch (Throwable th) {

        }

    }
}
