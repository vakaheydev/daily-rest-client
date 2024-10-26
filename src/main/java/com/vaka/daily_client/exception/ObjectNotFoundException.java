package com.vaka.daily_client.exception;

import lombok.Getter;


/**
 * Common exception if object not found by ID, unique name or both
 */
@Getter
public class ObjectNotFoundException extends RuntimeException {
    public enum ObjectNotFoundType {
        BY_NAME, BY_ID, BY_BOTH;
    }

    ObjectNotFoundType notFoundType;
    private String objectName;
    private Integer id;
    private String name;

    public ObjectNotFoundException(String objectName, Integer id, String name) {
        super(String.format("%s with ID {%d} and unique name (%s) not found", objectName, id, name));
        this.notFoundType = ObjectNotFoundType.BY_BOTH;
        this.id = id;
        this.name = name;
        this.objectName = objectName;
    }

    public ObjectNotFoundException(String objectName, Integer id) {
        super(String.format("%s with ID {%d} not found", objectName, id));
        this.notFoundType = ObjectNotFoundType.BY_ID;
        this.id = id;
        this.objectName = objectName;
    }

    public ObjectNotFoundException(String objectName, String name) {
        super(String.format("%s with unique name {%s} not found", objectName, name));
        this.notFoundType = ObjectNotFoundType.BY_NAME;
        this.name = name;
        this.objectName = objectName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
