package io.reflectoring.buckpal.application.port.in;

import io.reflectoring.buckpal.domain.Account;

public interface AccountLockUseCase {

	void lockAccount(Account.AccountId accountId);

	void releaseAccount(Account.AccountId accountId);

}
