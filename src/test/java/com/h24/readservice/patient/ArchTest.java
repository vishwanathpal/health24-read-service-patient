package com.h24.readservice.patient;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.h24.readservice.patient");

        noClasses()
            .that()
                .resideInAnyPackage("com.h24.readservice.patient.service..")
            .or()
                .resideInAnyPackage("com.h24.readservice.patient.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.h24.readservice.patient.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
