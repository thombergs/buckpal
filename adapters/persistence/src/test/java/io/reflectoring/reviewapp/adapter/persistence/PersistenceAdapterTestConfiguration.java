package io.reflectoring.reviewapp.adapter.persistence;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@Import(PersistenceAdapterConfiguration.class)
class PersistenceAdapterTestConfiguration {
}
