package io.reflectoring.buckpal.application.domain.model;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * An account that holds a certain amount of money. An {@link Account} object only
 * contains a window of the latest account activities. The total balance of the account is
 * the sum of a baseline balance that was valid before the first activity in the
 * window and the sum of the activity values.
 *
 * @param id              The unique ID of the account.
 * @param baselineBalance The baseline balance of the account. This was the balance of the account before the first activity in the activityWindow.
 * @param activityWindow  The window of latest activities on this account.
 */
public record Account(AccountId id, Money baselineBalance, ActivityWindow activityWindow) {

    /**
     * Creates an {@link Account} entity without an ID. Use to create a new entity that is not yet
     * persisted.
     */
    public static Account withoutId(Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(null, baselineBalance, activityWindow);
    }

    /**
     * Creates an {@link Account} entity with an ID. Use to reconstitute a persisted entity.
     */
    public static Account withId(AccountId accountId, Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

    public Optional<AccountId> getId() {
        return Optional.ofNullable(this.id);
    }

    /**
     * Calculates the total balance of the account by adding the activity values to the baseline balance.
     */
    public Money calculateBalance() {
        return Money.add(this.baselineBalance, this.activityWindow.calculateBalance(this.id));
    }

    /**
     * Tries to withdraw a certain amount of money from this account.
     * If successful, creates a new activity with a negative value.
     *
     * @return true if the withdrawal was successful, false if not.
     */
    public boolean withdraw(Money money, AccountId targetAccountId) {

        if (!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawal = Activity.of(this.id, this.id, targetAccountId, LocalDateTime.now(), money);
        this.activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(this.calculateBalance(), money.negate()).isPositiveOrZero();
    }

    /**
     * Tries to deposit a certain amount of money to this account.
     * If successful, creates a new activity with a positive value.
     *
     * @return true if the deposit was successful, false if not.
     */
    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity deposit = Activity.of(this.id, sourceAccountId, this.id, LocalDateTime.now(), money);
        this.activityWindow.addActivity(deposit);
        return true;
    }

    public record AccountId(Long value) {
    }

}
