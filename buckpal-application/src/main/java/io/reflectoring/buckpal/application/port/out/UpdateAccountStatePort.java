package io.reflectoring.buckpal.application.port.out;

import io.reflectoring.buckpal.domain.Account;

public interface UpdateAccountStatePort {

	void updateActivities(Account account);

}
