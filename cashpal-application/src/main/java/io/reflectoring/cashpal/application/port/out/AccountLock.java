package io.reflectoring.cashpal.application.port.out;

import io.reflectoring.cashpal.domain.Account;

public interface AccountLock {

	void lockAccount(Account.AccountId accountId);

	void releaseAccount(Account.AccountId accountId);

}
