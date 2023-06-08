package io.reflectoring.buckpal.adapter.out.persistence;

import io.reflectoring.buckpal.application.port.out.AccountLock;
import io.reflectoring.buckpal.application.domain.model.Account.AccountId;
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
