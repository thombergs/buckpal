package io.reflectoring.cashpal.application.port.out;

import java.time.LocalDateTime;

import io.reflectoring.cashpal.domain.Account;
import io.reflectoring.cashpal.domain.Account.AccountId;

public interface LoadAccountPort {

	Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
