package io.reflectoring.buckpal.account.application.service;

import io.reflectoring.buckpal.account.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Configuration properties for money transfer use cases.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransferProperties {

  private Money maximumTransferThreshold = Money.of(1_000_000L);

}
