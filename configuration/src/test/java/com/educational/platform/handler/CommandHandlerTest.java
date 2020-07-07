package com.educational.platform.handler;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = "com.educational.platform")
public class CommandHandlerTest {

    @ArchTest
    public static final ArchRule commandHandlers_shouldHave_nullabilityAnnotations = methods()
            .that().arePublic()
            .and().doNotHaveRawReturnType(new DescribedPredicate<>("native or void") {
                @Override
                public boolean apply(JavaClass javaClass) {
                    return javaClass.isPrimitive() || javaClass.isEquivalentTo(Void.TYPE);
                }
            })
            .and().areAnnotatedWith(CommandHandler.class)
            .should().beAnnotatedWith(NonNull.class).orShould().beAnnotatedWith(Nullable.class)
            .because("Command handlers should provide detailed documentation of API, it's why nullability annotations are needed.");

    @ArchTest
    public static final ArchRule commands_shouldBe_immutable = fields()
            .that().areDeclaredInClassesThat().haveSimpleNameEndingWith("Command")
            .should().beFinal()
            .because("Commands should be immutable.");

}
