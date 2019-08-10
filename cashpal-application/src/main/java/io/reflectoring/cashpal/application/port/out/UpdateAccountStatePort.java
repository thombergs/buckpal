package io.reflectoring.cashpal.application.port.out;

import io.reflectoring.cashpal.domain.Account;

public interface UpdateAccountStatePort {

	void updateActivities(Account account);

}
