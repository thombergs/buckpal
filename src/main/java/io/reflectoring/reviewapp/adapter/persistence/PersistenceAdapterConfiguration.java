package io.reflectoring.reviewapp.adapter.persistence;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.JdbcConfiguration;

@Configuration
@EnableJdbcRepositories
@ComponentScan
class PersistenceAdapterConfiguration extends JdbcConfiguration {

}
