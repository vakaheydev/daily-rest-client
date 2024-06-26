package com.vaka.daily_client.model;

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
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                ", schedule=(" + schedule.getId() + "," + schedule.getName() +
                ")}";
    }
}
