package com.vaka.daily.errorhandling.handler;

import com.vaka.daily.model.ResponseError;

public class DomainErrorHandlerUtils {
    public record NotFoundExceptionType(String typeName, String value) {
        public boolean causedById() {
            return typeName.equals("id");
        }

        public boolean causedByName() {
            return typeName.equals("unique_name");
        }

        public Integer getId() {
            return Integer.parseInt(this.value());
        }
    }

    public static NotFoundExceptionType resolveNotFoundException(ResponseError error) {
        if (error.getMessage().contains("ID {")) {
            String id = error.getMessage().split("\\{")[1].split("}")[0];
            return new NotFoundExceptionType("id", id);
        } else if (error.getMessage().contains("unique name {")) {
            String name = error.getMessage().split("\\{")[1].split("}")[0];
            return new NotFoundExceptionType("unique_name", name);
        } else {
            throw new RuntimeException(
                    "Error during resolving not found exception with message: " + error.getMessage());
        }
    }
}
