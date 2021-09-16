package decorator.implementations;

import decorator.interfaces.Decorator;
import entities.User;
import entities.implementations.PhoneNotification;
import entities.interfaces.Notification;
import locale.UserLocaleNotificationTemplate;
import locale.Translate;

public class DecoratorImpl implements Decorator {

    private final Notification notification;

    public DecoratorImpl(Notification notification) {
        this.notification = notification;
    }

    @Override
    public String getNotificationBody() {
        switch (notification.getUser().getLocale()) {
            case ENGLISH -> {
                if (PhoneNotification.class.equals(notification.getClass())) {
                    return notification.getUser().getName() + " " +
                            Translate.EN + " " + UserLocaleNotificationTemplate.PhoneNotification;
                }
                return notification.getUser().getName() + " " +
                        Translate.EN + " " + UserLocaleNotificationTemplate.PushNotification;
            }
            case FRENCH -> {
                if (PhoneNotification.class.equals(notification.getClass())) {
                    return notification.getUser().getName() + " " +
                            Translate.FR + " " + UserLocaleNotificationTemplate.PhoneNotification;
                }
                return notification.getUser().getName() + " " +
                        Translate.FR + " " + UserLocaleNotificationTemplate.PushNotification;
            }
            case GERMAN -> {
                if (PhoneNotification.class.equals(notification.getClass())) {
                    return notification.getUser().getName() + " " +
                            Translate.DE + " " + UserLocaleNotificationTemplate.PhoneNotification;
                }
                return notification.getUser().getName() + " " +
                        Translate.DE + " " + UserLocaleNotificationTemplate.PushNotification;
            }
            case ITALIAN -> {
                if (PhoneNotification.class.equals(notification.getClass())) {
                    return notification.getUser().getName() + " " +
                            Translate.IT + " " + UserLocaleNotificationTemplate.PhoneNotification;
                }
                return notification.getUser().getName() + " " +
                        Translate.IT + " " + UserLocaleNotificationTemplate.PushNotification;
            }
            case JAPANESE -> {
                if (PhoneNotification.class.equals(notification.getClass())) {
                    return notification.getUser().getName() + " " +
                            Translate.JP + " " + UserLocaleNotificationTemplate.PhoneNotification;
                }
                return notification.getUser().getName() + " " +
                        Translate.JP + " " + UserLocaleNotificationTemplate.PushNotification;
            }
            case KOREAN -> {
                if (PhoneNotification.class.equals(notification.getClass())) {
                    return notification.getUser().getName() + " " +
                            Translate.KR + " " + UserLocaleNotificationTemplate.PhoneNotification;
                }
                return notification.getUser().getName() + " " +
                        Translate.KR + " " + UserLocaleNotificationTemplate.PushNotification;
            }
            case SIMPLIFIED_CHINESE -> {
                if (PhoneNotification.class.equals(notification.getClass())) {
                    return notification.getUser().getName() + " " +
                            Translate.SIMPLIFIED_CH + " " + UserLocaleNotificationTemplate.PhoneNotification;
                }
                return notification.getUser().getName() + " " +
                        Translate.SIMPLIFIED_CH + " " + UserLocaleNotificationTemplate.PushNotification;
            }
            default -> {
                if (PhoneNotification.class.equals(notification.getClass())) {
                    return notification.getUser().getName() + " " +
                            Translate.RU + " " + UserLocaleNotificationTemplate.PhoneNotification;
                }
                return notification.getUser().getName() + " " +
                        Translate.RU + " " + UserLocaleNotificationTemplate.PushNotification;
            }
        }
    }

    @Override
    public User getUser() {
        return notification.getUser();
    }

}
