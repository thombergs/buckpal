package io.reflectoring.reviewapp.adapter.persistence;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import(PersistenceAdapterConfiguration.class)
public class PersistenceAdapterTestConfiguration {
}
