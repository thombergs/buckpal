package io.reflectoring.cashpal.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

/**
 * A money transfer activity between {@link Account}s.
 */
@Value
public class Activity {

	@Getter
	private final ActivityId id;

	/**
	 * The account that owns this activity.
	 */
	@Getter
	@NonNull
	private final Account.AccountId ownerAccountId;

	/**
	 * The debited account.
	 */
	@Getter
	@NonNull
	private final Account.AccountId sourceAccountId;

	/**
	 * The credited account.
	 */
	@Getter
	@NonNull
	private final Account.AccountId targetAccountId;

	/**
	 * The timestamp of the activity.
	 */
	@Getter
	@NonNull
	private final LocalDateTime timestamp;

	/**
	 * The money that was transferred between the accounts.
	 */
	@Getter
	@NonNull
	private final Money money;

	@Value
	public static class ActivityId {
		private final Long value;
	}

}
