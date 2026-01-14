package com.educational.platform.courses.course.create;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

/**
 * Create course command.
 */
public record CreateCourseCommand(@NotBlank String name, @NotBlank String description,
                                  List<CreateCurriculumItemCommand> curriculumItems) {

    public static CreateCourseCommandBuilder builder() {
        return new CreateCourseCommandBuilder();
    }

    public static final class CreateCourseCommandBuilder {
        private String name;
        private String description;
        private List<CreateCurriculumItemCommand> curriculumItems;

        private CreateCourseCommandBuilder() {
        }

        public CreateCourseCommandBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateCourseCommandBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateCourseCommandBuilder curriculumItems(List<CreateCurriculumItemCommand> curriculumItems) {
            this.curriculumItems = curriculumItems;
            return this;
        }

        public CreateCourseCommand build() {
            return new CreateCourseCommand(name, description, curriculumItems);
        }
    }
}
