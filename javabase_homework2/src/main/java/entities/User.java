package entities;

import locale.UserLocale;

public class User {

    private final long id;
    private final String name;
    private final String email;
    private final String phone;
    private final UserLocale userLocale;

    public User(long id, String name, String email, String phone, UserLocale userLocale) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userLocale = userLocale;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public UserLocale getLocale() {
        return userLocale;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", locale=" + userLocale +
                '}';
    }

}
