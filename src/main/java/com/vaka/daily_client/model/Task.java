package com.vaka.daily_client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vaka.daily_client.model.serialization.TaskDeserializer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
