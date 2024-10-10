package com.vaka.daily_client.model;

public enum UserTypes {
    USER(new UserType(1, "user")),
    VIP(new UserType(2, "vip")),
    ADMIN(new UserType(3, "admin")),
    DEVELOPER(new UserType(4, "developer"));

    private UserType type;

    public UserType getType() {
        return type;
    }

    UserTypes(UserType type) {
        this.type = type;
    }
}
