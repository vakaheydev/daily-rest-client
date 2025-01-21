package com.vaka.daily_client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vaka.daily_client.model.serialization.TaskDeserializer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.internal.ObjectArrayElementComparisonStrategy;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @NotEmpty
    @Size(max = 100)
    private String description;

    private LocalDateTime deadline;

    private Boolean status;

    private Integer scheduleId;

    private TaskType taskType;

    @JsonProperty("taskTypeId")
    public Integer getTaskTypeId() {
        Objects.requireNonNull(taskType);

        return taskType.getId();
    }
}
