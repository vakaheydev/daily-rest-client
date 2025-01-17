package com.vaka.daily_client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskType {
    public static final TaskType SINGULAR = new TaskType(1, "singular");
    public static final TaskType REPETITIVE = new TaskType(2, "repetitive");
    public static final TaskType REGULAR = new TaskType(3, "regular");

    private Integer id;
    private String name;

    public static TaskType of(String name) {
        return switch (name) {
            case "singular" -> new TaskType(1, "singular");
            case "repetitive" -> new TaskType(2, "repetitive");
            case "regular" -> new TaskType(3, "regular");
            default -> throw new IllegalArgumentException("Unknown task type: " + name);
        };
    }
}
