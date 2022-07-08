package io.reflectoring.buckpal.account.application.service;

import io.reflectoring.buckpal.account.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.account.application.port.out.UpdateAccountStatePort;
import io.reflectoring.buckpal.account.domain.Account;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FakeAccountRepository implements LoadAccountPort, UpdateAccountStatePort {

    Map<Account.AccountId, Account> accounts = new HashMap();

    public void addAccount(Account account) {
        accounts.put(account.getId().get(), account);
    }

    @Override
    public Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate) {
        return accounts.get(accountId);
    }

    @Override
    public void updateActivities(Account account) {
        // no op
    }
}
