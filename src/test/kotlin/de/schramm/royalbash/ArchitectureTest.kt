package de.schramm.royalbash

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
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
    private val classes = ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DONT_INCLUDE_TESTS)
            .importPackages("de.schramm.royalbash")

    @Test
    fun `packages do not have cyclic dependencies`() {
        SlicesRuleDefinition.slices()
                .matching("de.schramm.royalbash.(*)..")
                .should()
                .beFreeOfCycles()
                .check(classes)}

    @Test
    fun `domain has no dependencies to other packages`() {
        ArchRuleDefinition.noClasses()
                .that()
                .resideInAPackage(domain)
                .should()
                .resideOutsideOfPackages(domain, "java..", "javax..", "kotlin..", "com..", "org..")
                .check(classes)}

    @Test
    fun `no packages depend on api`() {
        ArchRuleDefinition.classes()
                .that()
                .resideInAPackage(api)
                .should()
                .onlyBeAccessed()
                .byClassesThat()
                .resideInAPackage(api)
                .check(classes)}

    @Test
    fun `classes do not throw generic exceptions`() {
        GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS.check(classes)}

    @Test
    fun `onion architecture is obeyed`() {
        Architectures.layeredArchitecture()
                .layer("Infrastructure")
                .definedBy(infrastructure)
                .layer("Application")
                .definedBy(application)
                .layer("Domain")
                .definedBy(domain)
                .layer("Api")
                .definedBy(api)
                .whereLayer("Infrastructure")
                .mayNotBeAccessedByAnyLayer()
                .whereLayer("Api")
                .mayOnlyBeAccessedByLayers("Infrastructure")
                .whereLayer("Application")
                .mayOnlyBeAccessedByLayers("Api", "Infrastructure")
                .check(classes)}
}
