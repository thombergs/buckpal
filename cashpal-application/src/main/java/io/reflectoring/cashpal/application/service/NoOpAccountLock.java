package io.reflectoring.cashpal.application.service;

import io.reflectoring.cashpal.application.port.out.AccountLock;
import io.reflectoring.cashpal.domain.Account.AccountId;
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
