package io.reflectoring.reviewapp.adapter.morepersistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AnotherPersistenceAdapter {

  private final CrudRepository<AnotherEntity, Long> anotherRepository;

}
