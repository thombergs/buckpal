package io.reflectoring.buckpal;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import io.reflectoring.buckpal.archunit.HexagonalArchitecture;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTests {

	@Test
	void validateRegistrationContextArchitecture() {
		HexagonalArchitecture.basePackage("io.reflectoring.buckpal")

				.withDomainLayer("application.domain")

				.withAdaptersLayer("adapter")
				.incoming("in.web")
				.outgoing("out.persistence")
				.and()

				.withApplicationLayer("application")
				.incomingPorts("port.in")
				.outgoingPorts("port.out")
				.and()

				.withConfiguration("configuration")
				.check(new ClassFileImporter()
						.importPackages("io.reflectoring.buckpal.."));
	}

	@Test
	void testPackageDependencies() {
		noClasses()
				.that()
				.resideInAPackage("io.reflectoring.buckpal.application.domain.model..")
				.should()
				.dependOnClassesThat()
				.resideOutsideOfPackages(
						"io.reflectoring.buckpal.application.domain.model..",
						"lombok..",
						"java.."
				)
				.check(new ClassFileImporter()
						.importPackages("io.reflectoring.buckpal.."));
	}

}
