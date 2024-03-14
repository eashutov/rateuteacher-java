package ru.shutov.rateuteacher.enums;

public enum QuestionType {
    RADIO, COMMENT, CHECK;

    @Override
    public String toString() {
        return this.name();
    }
}
