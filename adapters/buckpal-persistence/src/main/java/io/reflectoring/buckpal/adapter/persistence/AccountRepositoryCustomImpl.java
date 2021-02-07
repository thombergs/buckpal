package io.reflectoring.buckpal.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryCustomImpl implements AccountRepositoryCustom<AccountJpaEntity, Long> {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void lockAccount(Long id) {
        AccountJpaEntity reference = entityManager.getReference(AccountJpaEntity.class, id);
        entityManager.refresh(reference, LockModeType.PESSIMISTIC_WRITE);
    }

    @Override
    public void releaseAccount(Long id) {
        AccountJpaEntity reference = entityManager.getReference(AccountJpaEntity.class, id);
        entityManager.refresh(reference, LockModeType.NONE);
    }
}
