package io.reflectoring.buckpal.application.port.out;

import java.time.LocalDateTime;

import io.reflectoring.buckpal.domain.Account;
import io.reflectoring.buckpal.domain.Account.AccountId;

public interface LoadAccountPort {

	Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
