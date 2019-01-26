package de.schramm.royalbash

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.core.importer.ImportOptions
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.Architectures
import com.tngtech.archunit.library.GeneralCodingRules
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition
import org.junit.jupiter.api.Test

class ArchitectureTest {

    private val domain = "..domain.."
    private val application = "..application.."
    private val infrastructure = "..infrastructure.."
    private val api = "..api.."
    private val royalBashClasses = ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DONT_INCLUDE_TESTS)
            .importPackages("de.schramm.royalbash")
    private val springClasses = ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DONT_INCLUDE_TESTS)
            .importPackages("org.springframework")
    private val spring = "..springframework.."

    @Test
    fun `packages do not have cyclic dependencies`() {
        SlicesRuleDefinition.slices()
                .matching("de.schramm.royalbash.(*)..")
                .should()
                .beFreeOfCycles()
                .check(royalBashClasses)}

    @Test
    fun `domain has no dependencies to other packages`() {
        ArchRuleDefinition.noClasses()
                .that()
                .resideInAPackage(domain)
                .should()
                .resideOutsideOfPackages(domain, "java..", "javax..", "kotlin..", "com..", "org..")
                .check(royalBashClasses)}

    @Test
    fun `classes do not throw generic exceptions`() {
        GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS.check(royalBashClasses)}

    @Test
    fun `onion architecture is obeyed`() {
        Architectures.layeredArchitecture()
                .layer("Api")
                .definedBy(api)
                .layer("Infrastructure")
                .definedBy(infrastructure)
                .layer("Application")
                .definedBy(application)
                .layer("Domain")
                .definedBy(domain)
                .whereLayer("Api")
                .mayNotBeAccessedByAnyLayer()
                .whereLayer("Infrastructure")
                .mayOnlyBeAccessedByLayers("Api")
                .whereLayer("Application")
                .mayOnlyBeAccessedByLayers("Api", "Infrastructure")
                .whereLayer("Domain")
                .mayOnlyBeAccessedByLayers("Api", "Infrastructure", "Application")
                .check(royalBashClasses)}
}
