package io.reflectoring.buckpal.common;

import java.time.LocalDateTime;

import io.reflectoring.buckpal.application.domain.model.Account.AccountId;
import io.reflectoring.buckpal.application.domain.model.Activity;
import io.reflectoring.buckpal.application.domain.model.Activity.ActivityId;
import io.reflectoring.buckpal.application.domain.model.Money;

public class ActivityTestData {

	public static ActivityBuilder defaultActivity(){
		return new ActivityBuilder()
				.withOwnerAccount(new AccountId(42L))
				.withSourceAccount(new AccountId(42L))
				.withTargetAccount(new AccountId(41L))
				.withTimestamp(LocalDateTime.now())
				.withMoney(Money.of(999L));
	}

	public static class ActivityBuilder {
		private ActivityId id;
		private AccountId ownerAccountId;
		private AccountId sourceAccountId;
		private AccountId targetAccountId;
		private LocalDateTime timestamp;
		private Money money;

		public ActivityBuilder withId(ActivityId id) {
			this.id = id;
			return this;
		}

		public ActivityBuilder withOwnerAccount(AccountId accountId) {
			this.ownerAccountId = accountId;
			return this;
		}

		public ActivityBuilder withSourceAccount(AccountId accountId) {
			this.sourceAccountId = accountId;
			return this;
		}

		public ActivityBuilder withTargetAccount(AccountId accountId) {
			this.targetAccountId = accountId;
			return this;
		}

		public ActivityBuilder withTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public ActivityBuilder withMoney(Money money) {
			this.money = money;
			return this;
		}

		public Activity build() {
			return new Activity(
					this.id,
					this.ownerAccountId,
					this.sourceAccountId,
					this.targetAccountId,
					this.timestamp,
					this.money);
		}
	}
}
