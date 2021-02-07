package io.reflectoring.buckpal.application.service;

import io.reflectoring.buckpal.application.port.in.AccountLockUseCase;
import io.reflectoring.buckpal.application.port.out.AccountLockPort;
import io.reflectoring.buckpal.domain.Account.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AccountLockService implements AccountLockUseCase {

	private final AccountLockPort port;

	@Override
	public void lockAccount(AccountId accountId) {
		port.lockAccount(accountId);
	}

	@Override
	public void releaseAccount(AccountId accountId) {
		port.releaseAccount(accountId);
	}

}
