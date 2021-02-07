package io.reflectoring.buckpal.application.port.in;

import io.reflectoring.buckpal.domain.Account;

public interface AccountLock {

	void lockAccount(Account.AccountId accountId);

	void releaseAccount(Account.AccountId accountId);

}
