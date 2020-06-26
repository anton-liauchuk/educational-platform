package com.educational.platform;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.educational.platform")
public class ArchitectureTest {

    public static final String WEB_PACKAGE = "..web..";

    @ArchTest
    public static final ArchRule web_must_not_access_repositories_directly = noClasses()
            .that().resideInAnyPackage(WEB_PACKAGE)
            .should().accessClassesThat().areAssignableFrom(Repository.class);

}
