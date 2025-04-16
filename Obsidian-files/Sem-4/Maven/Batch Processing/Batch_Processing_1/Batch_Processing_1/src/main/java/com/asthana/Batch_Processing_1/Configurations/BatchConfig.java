package com.asthana.Batch_Processing_1.Configurations;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.asthana.Batch_Processing_1.Entity.Iris;

@Configuration
public class BatchConfig {

    @Bean
    public Job jobBean(JobRepository jobRepository, JobCompletionNotificationImpl listener, Step steps) {

        return new JobBuilder("job", jobRepository)
                .listener(listener)
                .start(steps)
                .build();
    }

    @Bean
    public Step steps(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
            ItemReader<Iris> reader, ItemProcessor<Iris, Iris> processor, ItemWriter<Iris> writer) {
        return new StepBuilder("jobStep", jobRepository)
                .<Iris, Iris>chunk(5, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public FlatFileItemReader<Iris> reader() {
        return new FlatFileItemReaderBuilder<Iris>()
                .name("itemReader")
                .resource(new ClassPathResource("Irise.csv"))
                .delimited()                
                .names("sepal_length", "sepal_width", "petal_length", "petal_width", "variety")
                .targetType(Iris.class)
                .linesToSkip(1) // This skips the header row
                .build();
    }

    @Bean
    public ItemProcessor<Iris, Iris> processor() {

        return new CustomitemProcessor();

    }

    @Bean
    public ItemWriter<Iris> writer(DataSource dataSource) {

        return new JdbcBatchItemWriterBuilder<Iris>()
                .sql("insert into iris(sepal_length,sepal_width,petal_length,petal_width,variety,total) values(:sepal_length,:sepal_width,:petal_length,:petal_width,:variety ,:total)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public JobCompletionNotificationImpl listener() {
        return new JobCompletionNotificationImpl();
    }
};