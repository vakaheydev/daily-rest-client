package com.vaka.daily_client.exception;

import java.util.HashMap;
import java.util.Map;

public class DuplicateEntityException extends RuntimeException {
    private final String objectType;
    private final Map<String, String> duplicates;

    public DuplicateEntityException(String objectType, String duplicateData) {
        super(String.format("%s: {(%s)}", objectType, duplicateData));
        this.objectType = objectType;
        duplicates = getMapDuplicates(duplicateData);
    }

    private Map<String, String> getMapDuplicates(String duplicateData) {
        int equalsBound = duplicateData.indexOf("=");

        String[] keys = duplicateData.substring(12, equalsBound - 1).split(", ");
        String[] values = duplicateData.substring(equalsBound + 2, duplicateData.length() - 1).split(", ");

        if (keys.length != values.length) {
            throw new IllegalArgumentException("Invalid duplicate data: " + duplicateData);
        }

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }

        return map;
    }

    public String getObjectType() {
        return objectType;
    }

    public Map<String, String> getDuplicates() {
        return duplicates;
    }
}
