package io.reflectoring.buckpal.application.domain.model;

import lombok.NonNull;

import java.time.LocalDateTime;

/**
 * A money transfer activity between {@link Account}s.
 *
 * @param id              The id of the activity.
 * @param ownerAccountId  The account that owns this activity.
 * @param sourceAccountId The debited account.
 * @param targetAccountId The credited account.
 * @param timestamp       The timestamp of the activity.
 * @param money           The money that was transferred between the accounts.
 */

public record Activity(ActivityId id, @NonNull Account.AccountId ownerAccountId,
                       @NonNull Account.AccountId sourceAccountId, @NonNull Account.AccountId targetAccountId,
                       @NonNull LocalDateTime timestamp, @NonNull Money money) {

    public static Activity of(
            @NonNull Account.AccountId ownerAccountId,
            @NonNull Account.AccountId sourceAccountId,
            @NonNull Account.AccountId targetAccountId,
            @NonNull LocalDateTime timestamp,
            @NonNull Money money) {
        return new Activity(null, ownerAccountId, sourceAccountId, targetAccountId, timestamp, money);
    }

    public record ActivityId(Long value) {
    }

}
