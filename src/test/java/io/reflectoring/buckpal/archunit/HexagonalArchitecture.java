package io.reflectoring.buckpal.archunit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tngtech.archunit.core.domain.JavaClasses;

public class HexagonalArchitecture extends ArchitectureElement {

  private Adapters adapters;
  private ApplicationLayer applicationLayer;
  private String configurationPackage;
  private List<String> domainPackages = new ArrayList<>();

  public static HexagonalArchitecture basePackage(String basePackage) {
    return new HexagonalArchitecture(basePackage);
  }

  public HexagonalArchitecture(String basePackage) {
    super(basePackage);
  }

  public Adapters withAdaptersLayer(String adaptersPackage) {
    this.adapters = new Adapters(this, fullQualifiedPackage(adaptersPackage));
    return this.adapters;
  }

  public HexagonalArchitecture withDomainLayer(String domainPackage) {
    this.domainPackages.add(fullQualifiedPackage(domainPackage));
    return this;
  }

  public ApplicationLayer withApplicationLayer(String applicationPackage) {
    this.applicationLayer = new ApplicationLayer(fullQualifiedPackage(applicationPackage), this);
    return this.applicationLayer;
  }

  public HexagonalArchitecture withConfiguration(String packageName) {
    this.configurationPackage = fullQualifiedPackage(packageName);
    return this;
  }

  private void domainDoesNotDependOnAdapters(JavaClasses classes) {
    denyAnyDependency(
        this.domainPackages, Collections.singletonList(adapters.basePackage), classes);
  }

  public void check(JavaClasses classes) {
    this.adapters.doesNotContainEmptyPackages();
    this.adapters.dontDependOnEachOther(classes);
    this.adapters.doesNotDependOn(this.configurationPackage, classes);
    this.applicationLayer.doesNotContainEmptyPackages();
    this.applicationLayer.doesNotDependOn(this.adapters.getBasePackage(), classes);
    this.applicationLayer.doesNotDependOn(this.configurationPackage, classes);
    this.applicationLayer.incomingAndOutgoingPortsDoNotDependOnEachOther(classes);
    this.domainDoesNotDependOnAdapters(classes);
  }
}
