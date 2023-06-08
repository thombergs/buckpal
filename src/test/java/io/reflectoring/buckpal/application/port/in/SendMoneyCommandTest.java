package io.reflectoring.buckpal.application.port.in;

import io.reflectoring.buckpal.application.domain.model.Account;
import io.reflectoring.buckpal.application.domain.model.Money;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class SendMoneyCommandTest {

    @Test
    public void validationOk() {
        new SendMoneyCommand(
                new Account.AccountId(42L),
                new Account.AccountId(43L),
                new Money(new BigInteger("10")));
        // no exception
    }

    @Test
    public void moneyValidationFails() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            new SendMoneyCommand(
                    new Account.AccountId(42L),
                    new Account.AccountId(43L),
                    new Money(new BigInteger("-10")));
        });
    }

    @Test
    public void accountIdValidationFails() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            new SendMoneyCommand(
                    new Account.AccountId(42L),
                    null,
                    new Money(new BigInteger("10")));
        });
    }

}