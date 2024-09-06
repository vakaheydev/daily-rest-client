package com.vaka.dailyClient.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Task {
    public Task(Integer id, String name, String description, LocalDateTime deadline, Boolean status,
                Schedule schedule) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.schedule = schedule;
    }

    public Task() {
    }

    private Integer id;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @NotEmpty
    @Size(max = 100)
    private String description;

    private LocalDateTime deadline;

    private Boolean status;

    @JsonBackReference
    private Schedule schedule;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Task {");

        sb.append(String.format("id=%d", id));
        sb.append(String.format(", name=%s", name));
        sb.append(String.format(", description=%s", description));
        sb.append(String.format(", deadline=%s", deadline));
        sb.append(String.format(", status=%s", status));
        sb.append(", schedule=");

        if (schedule != null) {
            sb.append(String.format("(%d, ", schedule.getId()));
            sb.append(String.format("%s)", schedule.getName()));
        }

        sb.append("}");

        return sb.toString();
    }
}
