package unq.tpi.desapp.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;



@Ignore
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "unq.tpi.desapp")
public class ArchitectureTest {

    @ArchTest
    private final ArchRule modelClassesShouldBePublic =
            classes().that().resideInAPackage("..model..")
                    .should().bePublic();

    @ArchTest
    private final ArchRule modelClassesShouldBeAnnotatedWithEntityAnnotation =
            classes().that().resideInAPackage("..model..")
                    .should().beAnnotatedWith(Entity.class);

    @ArchTest
    private final ArchRule controllerClassesShouldBeAnnotatedWithControllerAnnotation =
            classes().that().resideInAPackage("..controllers..")
                    .should().beAnnotatedWith(Controller.class);

    @ArchTest
    private final ArchRule controllerClassesShouldHaveSimpleNameEndingWithController =
            classes().that().resideInAPackage("..controllers..")
                    .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    private final ArchRule persistanceClassesShouldOnlyBeAccessedByControllers =
        classes().that().resideInAPackage("..persistance..")
                .should().onlyBeAccessed().byAnyPackage("..controllers..");

}

