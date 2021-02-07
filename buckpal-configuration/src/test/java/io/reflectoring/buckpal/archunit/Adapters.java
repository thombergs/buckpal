package io.reflectoring.buckpal.archunit;

import java.util.ArrayList;
import java.util.List;

import com.tngtech.archunit.core.domain.JavaClasses;

public class Adapters extends ArchitectureElement {

  private final HexagonalArchitecture parentContext;
  private List<String> incomingAdapterPackages = new ArrayList<>();
  private List<String> outgoingAdapterPackages = new ArrayList<>();

  Adapters(HexagonalArchitecture parentContext, String basePackage) {
    super(basePackage);
    this.parentContext = parentContext;
  }

  public Adapters outgoing(String packageName) {
    this.incomingAdapterPackages.add(fullQualifiedPackage(packageName));
    return this;
  }

  public Adapters incoming(String packageName) {
    this.outgoingAdapterPackages.add(fullQualifiedPackage(packageName));
    return this;
  }

  List<String> allAdapterPackages() {
    List<String> allAdapters = new ArrayList<>();
    allAdapters.addAll(incomingAdapterPackages);
    allAdapters.addAll(outgoingAdapterPackages);
    return allAdapters;
  }

  public HexagonalArchitecture and() {
    return parentContext;
  }

  String getBasePackage() {
    return basePackage;
  }

  void dontDependOnEachOther(JavaClasses classes) {
    List<String> allAdapters = allAdapterPackages();
    for (String adapter1 : allAdapters) {
      for (String adapter2 : allAdapters) {
        if (!adapter1.equals(adapter2)) {
          denyDependency(adapter1, adapter2, classes);
        }
      }
    }
  }

  void doesNotDependOn(String packageName, JavaClasses classes) {
    denyDependency(this.basePackage, packageName, classes);
  }

  void doesNotContainEmptyPackages() {
    denyEmptyPackages(allAdapterPackages());
  }
}
