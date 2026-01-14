package com.educational.platform.courses.course.create;

public class CreateLectureCommand extends CreateCurriculumItemCommand {

    private final String text;

    public CreateLectureCommand(String title, String description, Integer serialNumber, String text) {
        super(title, description, serialNumber);
        this.text = text;
    }

    public static CreateLectureCommandBuilder builder() {
        return new CreateLectureCommandBuilder();
    }

    public String getText() {
        return text;
    }

    public static final class CreateLectureCommandBuilder {
        private String text;
        private String title;
        private String description;
        private Integer serialNumber;

        private CreateLectureCommandBuilder() {
        }

        public CreateLectureCommandBuilder text(String text) {
            this.text = text;
            return this;
        }

        public CreateLectureCommandBuilder title(String title) {
            this.title = title;
            return this;
        }

        public CreateLectureCommandBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateLectureCommandBuilder serialNumber(Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public CreateLectureCommand build() {
            return new CreateLectureCommand(title, description, serialNumber, text);
        }
    }
}
