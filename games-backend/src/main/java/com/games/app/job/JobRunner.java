package com.games.app.job;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.games.app.constants.Constants;

@Component
public class JobRunner {

	private JobLauncher simpleJobLauncher;

	private Job fileProcessJob;

	private static final Logger LOGGER = LoggerFactory.getLogger(JobRunner.class);

	@Autowired
	public JobRunner(Job fileProcessJob, JobLauncher jobLauncher) {
		this.simpleJobLauncher = jobLauncher;
		this.fileProcessJob = fileProcessJob;
	}

	@Async
	public synchronized void runBatchJob(String fileHeader, String file) {
		try {
			LOGGER.info("Job-----started");

			String filePath = file;
			File tempFile = new File(filePath);
			if (tempFile.exists()) {

				JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
				jobParametersBuilder.addString(Constants.FILE_NAME_CONTEXT_KEY, filePath);
				jobParametersBuilder.addString(Constants.FILEHEADER_CONTEXT_KEY, fileHeader);
				jobParametersBuilder.addDate(Constants.DATE_CONTEXT_KEY, new Date(), true);

				BatchStatus status = runJob(fileProcessJob, jobParametersBuilder.toJobParameters());
				//moveFile(filePath, outPath);
				LOGGER.info(fileProcessJob + ":" + status.getBatchStatus().toString());
			} else {
				LOGGER.error("There is no file in dir to process");
			}

		} catch (Throwable th) {
			LOGGER.error(th.getMessage().toString());
		}

	}

	public BatchStatus runJob(Job job, JobParameters parameters) {
		BatchStatus status = null;
		try {
			LOGGER.info("Job with jobName={} is started running.",
					parameters.getParameters().get(Constants.JOB_NAME_CONTEXT_KEY));
			JobExecution jobExecution = simpleJobLauncher.run(job, parameters);
			status = jobExecution.getStatus();

		} catch (JobExecutionAlreadyRunningException e) {
			LOGGER.info("Job with jobName={} is already running.",
					parameters.getParameters().get(Constants.JOB_NAME_CONTEXT_KEY));
		} catch (JobRestartException e) {
			LOGGER.info("Job with jobName={} was not restarted.",
					parameters.getParameters().get(Constants.JOB_NAME_CONTEXT_KEY));
		} catch (JobInstanceAlreadyCompleteException e) {
			LOGGER.info("Job with jobName={} already completed.",
					parameters.getParameters().get(Constants.JOB_NAME_CONTEXT_KEY));
		} catch (JobParametersInvalidException e) {
			LOGGER.info("Invalid job parameters.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
		}
		return status;

	}

	public boolean moveFile(String fromPath, String toPath) {

		Path temp;
		try {
			temp = Files.move(Paths.get(fromPath), Paths.get(toPath));

			if (temp != null) {
				LOGGER.error("File renamed and moved successfully");
			} else {
				LOGGER.error("Failed to move the file");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

}
