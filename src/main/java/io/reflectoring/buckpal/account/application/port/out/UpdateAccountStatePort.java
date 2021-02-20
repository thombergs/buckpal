package io.reflectoring.buckpal.account.application.port.out;

import io.reflectoring.buckpal.account.domain.Account;

public interface UpdateAccountStatePort {

	void updateActivities(Account account);

}
