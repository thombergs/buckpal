package io.reflectoring.buckpal.adapter.persistence;

import io.reflectoring.buckpal.application.port.out.AccountLockPort;
import io.reflectoring.buckpal.common.PersistenceAdapter;
import io.reflectoring.buckpal.domain.Account;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.AvailableSettings;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PessimisticLockScope;
import java.util.HashMap;
import java.util.Map;

@PersistenceAdapter
@RequiredArgsConstructor
public class AccountLockPersistenceAdapter implements AccountLockPort {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public void lockAccount(Account.AccountId accountId) {
        AccountJpaEntity accountJpaEntity = this.em.find(AccountJpaEntity.class, accountId.getValue());
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.JPA_LOCK_SCOPE, PessimisticLockScope.EXTENDED);
        this.em.lock(accountJpaEntity, LockModeType.PESSIMISTIC_WRITE,properties);
    }

    @Override
    public void releaseAccount(Account.AccountId accountId) {
        AccountJpaEntity accountJpaEntity = this.em.find(AccountJpaEntity.class, accountId.getValue());
        this.em.lock(accountJpaEntity, LockModeType.NONE);
    }
}
