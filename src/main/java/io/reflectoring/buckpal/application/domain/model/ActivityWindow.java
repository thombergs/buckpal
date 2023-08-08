package io.reflectoring.buckpal.application.domain.model;

import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.*;

/**
 * A window of account activities.
 *
 * @param activities The list of account activities within this window.
 */
public record ActivityWindow(@NonNull List<Activity> activities) {

    public static ActivityWindow of(@NonNull Activity... activities) {
        return new ActivityWindow(new ArrayList<>(List.of(activities)));
    }

    /**
     * The timestamp of the first activity within this window.
     */
    public LocalDateTime getStartTimestamp() {
        return activities.stream()
                .min(Comparator.comparing(Activity::timestamp))
                .orElseThrow(IllegalStateException::new)
                .timestamp();
    }

    /**
     * The timestamp of the last activity within this window.
     *
     * @return
     */
    public LocalDateTime getEndTimestamp() {
        return activities.stream()
                .max(Comparator.comparing(Activity::timestamp))
                .orElseThrow(IllegalStateException::new)
                .timestamp();
    }

    /**
     * Calculates the balance by summing up the values of all activities within this window.
     */
    public Money calculateBalance(Account.AccountId accountId) {
        Money depositBalance = activities.stream()
                .filter(a -> a.targetAccountId().equals(accountId))
                .map(Activity::money)
                .reduce(Money.ZERO, Money::add);

        Money withdrawalBalance = activities.stream()
                .filter(a -> a.sourceAccountId().equals(accountId))
                .map(Activity::money)
                .reduce(Money.ZERO, Money::add);

        return Money.add(depositBalance, withdrawalBalance.negate());
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
}
