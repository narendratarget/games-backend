package com.games.app;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.games.app.job.JobRunner;

@SpringBootApplication
@EnableJpaRepositories
public class GamesBackendApplication {

	private static JobRunner jobRunner;
	
	@Autowired
	public GamesBackendApplication(JobRunner jobRunner) {
		this.jobRunner = jobRunner;
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GamesBackendApplication.class, args);
		
		Resource res=new ClassPathResource("gamesc2b2088.csv");
		jobRunner.runBatchJob("title,platform,score,genre,editors_choice",res.getFile().getAbsolutePath());
	}

}
