package com.cnd13natation.performances.batch.job;

import com.cnd13natation.performances.batch.common.JobConstants;
import com.cnd13natation.performances.batch.common.SwimmerCSV;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Job for the performance extraction from the CSV file
 *
 * @author Ritchie Nithoo
 */
@Slf4j
public class PerformanceExtractionJob {

  private final JobRepository jobRepository;

  private final JobLauncher jobLauncher;

  private final PlatformTransactionManager transactionManager;

  private final PerformanceHandler performanceHandler;

  public PerformanceExtractionJob(
      final JobRepository jobRepository,
      final JobLauncher jobLauncher,
      final PlatformTransactionManager transactionManager,
      final PerformanceHandler performanceHandler) {
    this.jobRepository = jobRepository;
    this.jobLauncher = jobLauncher;
    this.transactionManager = transactionManager;
    this.performanceHandler = performanceHandler;
  }

  public void launchPerformanceExtractionJob()
      throws JobInstanceAlreadyCompleteException,
          JobExecutionAlreadyRunningException,
          JobParametersInvalidException,
          JobRestartException {
    log.info("Starting performance extraction job");
    final var jobParameters = new JobParameters();
    jobLauncher.run(buildJob(), jobParameters);
    log.info("Performance job extraction ended");
  }

  private Step buildPerformanceExtractionStep() {
    return new StepBuilder(JobConstants.STEP_NAME, jobRepository)
        .<List<SwimmerCSV>, List<Swimmer>>chunk(10, transactionManager)
        .reader(performanceHandler.getPerformanceItemReader())
        .processor(performanceHandler.getPerformanceItemProcessor())
        .writer(performanceHandler.getPerformanceItemWriter())
        .build();
  }

  private Job buildJob() {
    return new JobBuilder(JobConstants.JOB_NAME, jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(buildPerformanceExtractionStep())
        .build();
  }
}
