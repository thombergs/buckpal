package io.reflectoring.buckpal.application.service;

import io.reflectoring.buckpal.application.port.out.AccountLock;
import io.reflectoring.buckpal.domain.Account.AccountId;
import org.springframework.stereotype.Component;

@Component
class NoOpAccountLock implements AccountLock {

	@Override
	public void lockAccount(AccountId accountId) {
		// do nothing
	}

	@Override
	public void releaseAccount(AccountId accountId) {
		// do nothing
	}

}
