package io.reflectoring.cashpal;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import io.reflectoring.cashpal.archunit.HexagonalArchitecture;
import org.junit.jupiter.api.Test;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

class DependencyRuleTests {

	@Test
	void validateRegistrationContextArchitecture() {
		HexagonalArchitecture.boundedContext("io.reflectoring.cashpal")

				.withDomainLayer("domain")

				.withAdaptersLayer("adapter")
				.incoming("web")
				.outgoing("persistence")
				.and()

				.withApplicationLayer("application")
				.services("service")
				.incomingPorts("port.in")
				.outgoingPorts("port.out")
				.and()

				.withConfiguration("configuration")
				.check(new ClassFileImporter()
						.importPackages("io.reflectoring.cashpal.."));
	}

	@Test
	void testPackageDependencies() {
		noClasses()
				.that()
				.resideInAPackage("io.reflectoring.reviewapp.domain..")
				.should()
				.dependOnClassesThat()
				.resideInAnyPackage("io.reflectoring.reviewapp.application..")
				.check(new ClassFileImporter()
						.importPackages("io.reflectoring.reviewapp.."));
	}

}
