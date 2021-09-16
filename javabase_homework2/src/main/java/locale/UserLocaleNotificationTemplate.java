package locale;

public enum UserLocaleNotificationTemplate {

    PushNotification("PushNotification"),
    PhoneNotification("PhoneNotification");

    private final String text;

    UserLocaleNotificationTemplate(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}