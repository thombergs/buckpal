package io.reflectoring.cashpal.application.service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import io.reflectoring.cashpal.application.port.in.SendMoneyUseCase;
import io.reflectoring.cashpal.application.port.out.AccountLock;
import io.reflectoring.cashpal.application.port.out.LoadAccountPort;
import io.reflectoring.cashpal.application.port.out.UpdateAccountStatePort;
import io.reflectoring.cashpal.domain.Account;
import io.reflectoring.cashpal.testdata.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

	private final LoadAccountPort loadAccountPort;
	private final AccountLock accountLock;
	private final UpdateAccountStatePort updateAccountStatePort;

	@Override
	public boolean sendMoney(SendMoneyCommand command) {
		LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

		Account sourceAccount = loadAccountPort.loadAccount(
				command.getSourceAccountId(),
				baselineDate);

		Account targetAccount = loadAccountPort.loadAccount(
				command.getTargetAccountId(),
				baselineDate);

		accountLock.lockAccount(sourceAccount.getId());
		if (!sourceAccount.withdraw(command.getMoney(), targetAccount.getId())) {
			accountLock.releaseAccount(sourceAccount.getId());
			return false;
		}

		accountLock.lockAccount(targetAccount.getId());
		if (!targetAccount.deposit(command.getMoney(), sourceAccount.getId())) {
			accountLock.releaseAccount(sourceAccount.getId());
			accountLock.releaseAccount(targetAccount.getId());
			return false;
		}

		updateAccountStatePort.updateActivities(sourceAccount);
		updateAccountStatePort.updateActivities(targetAccount);

		accountLock.releaseAccount(sourceAccount.getId());
		accountLock.releaseAccount(targetAccount.getId());
		return true;
	}

}
