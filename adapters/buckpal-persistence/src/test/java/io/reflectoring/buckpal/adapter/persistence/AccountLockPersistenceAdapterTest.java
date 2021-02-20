package io.reflectoring.buckpal.adapter.persistence;

import io.reflectoring.buckpal.domain.Account.AccountId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class AccountLockPersistenceAdapterTest {

    @Autowired
    private AccountLockPersistenceAdapter adapterUnderTest;

    @Autowired
    private AccountRepository accountRepository;

    private AccountJpaEntity account;

    @BeforeEach
    public void setup(){
        this.account = new AccountJpaEntity();
        this.account.setCreatedAt(LocalDate.now().minusDays(1));
        this.accountRepository.saveAndFlush(account);
    }

    @Test
    @Transactional
    public void locksAccount() {
        var accountId = new AccountId(this.account.getId());
        this.adapterUnderTest.lockAccount(accountId);
    }

    @Test
    @Transactional
    public void releasesAccount(){
        var accountId = new AccountId(this.account.getId());
        this.adapterUnderTest.lockAccount(accountId);
        this.adapterUnderTest.releaseAccount(accountId);
    }
}
