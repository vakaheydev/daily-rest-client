package com.vaka.daily.model;

import java.net.URI;
import java.util.Optional;

public enum DomainType {
    USER("api/user"),
    USER_TYPE("api/user_type"),
    SCHEDULE("api/schedule"),
    TASK("api/task");

    private final String path;

    DomainType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static Optional<DomainType> fromUrl(URI url) {
        try {
            return Optional.of(DomainType.valueOf(url.getPath().split("/")[2].toUpperCase()));
        } catch (IllegalStateException | ArrayIndexOutOfBoundsException ex) {
            return Optional.empty();
        }
    }
}
