package io.reflectoring.buckpal.application.port.out;

import io.reflectoring.buckpal.application.domain.model.Account;

public interface UpdateAccountStatePort {

	void updateActivities(Account account);

}
