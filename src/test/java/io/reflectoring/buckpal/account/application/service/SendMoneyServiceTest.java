package io.reflectoring.buckpal.account.application.service;

import io.reflectoring.buckpal.account.application.port.in.SendMoneyCommand;
import io.reflectoring.buckpal.account.application.port.out.AccountLock;
import io.reflectoring.buckpal.account.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.account.application.port.out.UpdateAccountStatePort;
import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.account.domain.ActivityWindow;
import io.reflectoring.buckpal.account.domain.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class SendMoneyServiceTest {

    FakeAccountRepository repo = new FakeAccountRepository();

    private final LoadAccountPort loadAccountPort = repo;

    private final AccountLock accountLock = new NoOpAccountLock();

    private final UpdateAccountStatePort updateAccountStatePort = repo;

    private final SendMoneyService sendMoneyService =
            new SendMoneyService(loadAccountPort, accountLock, updateAccountStatePort, moneyTransferProperties());

    @Test
    void sendMoney_accountHasNotEnoughMoney_transactionFails() {
        // arrange
        Account sourceAccount = Account.withId(new AccountId(1234L),
                new Money(BigInteger.valueOf(200)),
                new ActivityWindow(new ArrayList<>()));
        Account targetAccount = Account.withId(new AccountId(4321L),
                new Money(BigInteger.valueOf(0)),
                new ActivityWindow(new ArrayList<>()));
        repo.addAccount(sourceAccount);
        repo.addAccount(targetAccount);

        // act
        SendMoneyCommand command = new SendMoneyCommand(
                sourceAccount.getId().get(),
                targetAccount.getId().get(),
                Money.of(500L)); // withdraw more money than sourceAccount has
        boolean success = sendMoneyService.sendMoney(command);

        // assert
        assertThat(success).isFalse();
    }

    @Test
    void sendMoney_amountOK_transactionSucceeds() {
        // arrange
        Account sourceAccount = Account.withId(new AccountId(1234L),
                new Money(BigInteger.valueOf(1000)),
                new ActivityWindow(new ArrayList<>()));
        Account targetAccount = Account.withId(new AccountId(4321L),
                new Money(BigInteger.valueOf(0)),
                new ActivityWindow(new ArrayList<>()));
        repo.addAccount(sourceAccount);
        repo.addAccount(targetAccount);
        Money money = Money.of(500L);

        // act
        SendMoneyCommand command = new SendMoneyCommand(
                sourceAccount.getId().get(),
                targetAccount.getId().get(),
                money);
        boolean success = sendMoneyService.sendMoney(command);

        // assert
        assertThat(success).isTrue();
    }

    @Test
    void sendMoney_amountOK_moneyIsTransferred() {
        // arrange
        Account sourceAccount = Account.withId(new AccountId(1234L),
                new Money(BigInteger.valueOf(1000)),
                new ActivityWindow(new ArrayList<>()));
        Account targetAccount = Account.withId(new AccountId(4321L),
                new Money(BigInteger.valueOf(0)),
                new ActivityWindow(new ArrayList<>()));
        repo.addAccount(sourceAccount);
        repo.addAccount(targetAccount);

        // act
        SendMoneyCommand command = new SendMoneyCommand(
                sourceAccount.getId().get(),
                targetAccount.getId().get(),
                Money.of(300L));
        sendMoneyService.sendMoney(command);

        // assert
        Assertions.assertEquals(Money.of(700), sourceAccount.calculateBalance());
        Assertions.assertEquals(Money.of(300), targetAccount.calculateBalance());
    }

    private MoneyTransferProperties moneyTransferProperties() {
        return new MoneyTransferProperties(Money.of(Long.MAX_VALUE));
    }

}
