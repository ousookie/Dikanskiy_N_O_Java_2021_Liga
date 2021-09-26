package ru.dikanskiy.locale;

public enum UserLocale {

    RUSSIAN("Здравствуйте"),

    ENGLISH("Hello"),

    GERMAN("Güten Tag"),

    FRENCH("Bonjour"),

    ITALIAN("Ciao"),

    JAPANESE("こんにちは"),

    KOREAN("안녕하십니까"),

    SIMPLIFIED_CHINESE("你好");

    private final String translate;

    UserLocale(String translate) {
        this.translate = translate;
    }

    public String getTranslate() {
        return translate;
    }

}
