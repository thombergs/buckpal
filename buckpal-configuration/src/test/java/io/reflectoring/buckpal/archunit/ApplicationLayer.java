package io.reflectoring.buckpal.archunit;

import java.util.ArrayList;
import java.util.List;

import com.tngtech.archunit.core.domain.JavaClasses;

public class ApplicationLayer extends ArchitectureElement {

  private final HexagonalArchitecture parentContext;
  private List<String> incomingPortsPackages = new ArrayList<>();
  private List<String> outgoingPortsPackages = new ArrayList<>();
  private List<String> servicePackages = new ArrayList<>();

  public ApplicationLayer(String basePackage, HexagonalArchitecture parentContext) {
    super(basePackage);
    this.parentContext = parentContext;
  }

  public ApplicationLayer incomingPorts(String packageName) {
    this.incomingPortsPackages.add(fullQualifiedPackage(packageName));
    return this;
  }

  public ApplicationLayer outgoingPorts(String packageName) {
    this.outgoingPortsPackages.add(fullQualifiedPackage(packageName));
    return this;
  }

  public ApplicationLayer services(String packageName) {
    this.servicePackages.add(fullQualifiedPackage(packageName));
    return this;
  }

  public HexagonalArchitecture and() {
    return parentContext;
  }

  public void doesNotDependOn(String packageName, JavaClasses classes) {
    denyDependency(this.basePackage, packageName, classes);
  }

  public void incomingAndOutgoingPortsDoNotDependOnEachOther(JavaClasses classes) {
    denyAnyDependency(this.incomingPortsPackages, this.outgoingPortsPackages, classes);
    denyAnyDependency(this.outgoingPortsPackages, this.incomingPortsPackages, classes);
  }

  private List<String> allPackages() {
    List<String> allPackages = new ArrayList<>();
    allPackages.addAll(incomingPortsPackages);
    allPackages.addAll(outgoingPortsPackages);
    allPackages.addAll(servicePackages);
    return allPackages;
  }

  void doesNotContainEmptyPackages() {
    denyEmptyPackages(allPackages());
  }
}
