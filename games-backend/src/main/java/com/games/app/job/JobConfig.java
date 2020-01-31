package com.games.app.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.games.app.dto.GameRequest;
import com.games.app.model.Game;

@Configuration
@EnableBatchProcessing
public class JobConfig {

	@Autowired
	public DataSource dataSource;
	
	@Autowired
	ItemProcessor itemProcessor;

	@Bean
	/// @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<GameRequest> itemReader, ItemProcessor<GameRequest, Game> itemProcessor, ItemWriter<Game> itemWriter)
			throws Exception {

		Step step = stepBuilderFactory.get("fileProcessJob").<GameRequest, Game>chunk(100).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).build();

		return jobBuilderFactory.get("fileProcessJob").incrementer(new RunIdIncrementer()).start(step).build();
	}

	@Bean
	/// @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@StepScope
	Resource inputFileResource(@Value("#{jobParameters[fileName]}") final String fileName) throws Exception {
		return new FileSystemResource(fileName);
	}

	@Bean
	/// @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@StepScope
	public FlatFileItemReader<GameRequest> itemReader(@Value("#{jobParameters[fileHeader]}") final String fileHeader)
			throws Exception {
		FlatFileItemReader<GameRequest> reader = new FlatFileItemReader<>();

		reader.setResource(inputFileResource(null));

		// Set number of lines to skips. Use it if file has header rows.
		reader.setLinesToSkip(1);

		reader.setLineMapper(new DefaultLineMapper<GameRequest>() {

			{
				setLineTokenizer(new DelimitedLineTokenizer() {

					{
						setNames(fileHeader.split(","));
						setDelimiter(",");
					}
				});

				setFieldSetMapper(new GameRowMapper());
			}
		});
		return reader;
	}

	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
		simpleAsyncTaskExecutor.setConcurrencyLimit(5);
		return simpleAsyncTaskExecutor;
	}

}
