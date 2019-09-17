package io.reflectoring.buckpal.application.service;

import io.reflectoring.buckpal.application.port.in.SendMoneyUseCase;
import io.reflectoring.buckpal.application.port.out.AccountLock;
import io.reflectoring.buckpal.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.application.port.out.UpdateAccountStatePort;
import io.reflectoring.buckpal.domain.Account;
import io.reflectoring.buckpal.testdata.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@UseCase
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

	private final LoadAccountPort loadAccountPort;
	private final AccountLock accountLock;
	private final UpdateAccountStatePort updateAccountStatePort;
	private final MoneyTransferProperties moneyTransferProperties;

	@Override
	public boolean sendMoney(SendMoneyCommand command) {

		if(command.getMoney().isGreaterThan(moneyTransferProperties.getMaximumTransferThreshold())){
			throw new ThresholdExceededException(moneyTransferProperties.getMaximumTransferThreshold(), command.getMoney());
		}

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
