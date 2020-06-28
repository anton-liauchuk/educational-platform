package com.educational.platform.layer;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.educational.platform")
public class LayerTest {

    @ArchTest
    public static final ArchRule controllers_mustNotAccess_repositories = noClasses()
            .that().areAnnotatedWith(Controller.class)
            .should().accessClassesThat().areAssignableFrom(Repository.class)
            .because("Controllers should not contain repository calls, all logic should be executed via command/query handlers.");

}
