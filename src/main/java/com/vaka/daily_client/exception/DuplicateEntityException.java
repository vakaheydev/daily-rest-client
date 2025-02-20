package com.vaka.daily_client.exception;

public class DuplicateEntityException extends RuntimeException {
    private final String objectType;
    private final String[] duplicates;

    public DuplicateEntityException(String objectType, String duplicateData) {
        super(String.format("Duplicate %s: [%s]", objectType, duplicateData));
        this.objectType = objectType;
        this.duplicates = duplicateData.split(",");
    }

    public String getObjectType() {
        return objectType;
    }

    public String[] getDuplicates() {
        return duplicates;
    }
}
