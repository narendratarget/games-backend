package com.games.app.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.games.app.dto.GameRequest;
import com.games.app.model.Game;

@Component
@StepScope
/// @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GameProcessor implements ItemProcessor<GameRequest, Game> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameProcessor.class);

    @Value("#{jobParameters[jobName]}")
    private String jobName;

    @Override
    public Game process(GameRequest gameRequest) throws Exception {
       Game game=new Game();
       game.setTitle(gameRequest.getTitle());
       game.setPlatform(gameRequest.getPlatform());
       game.setScore(gameRequest.getScore());
       game.setGenre(gameRequest.getGenre());
       game.setEditors_choice(gameRequest.getEditors_choice());
       
        return game;
    }
}
