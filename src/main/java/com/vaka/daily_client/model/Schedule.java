package com.vaka.daily_client.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @JsonBackReference
    @JsonIgnoreProperties({"schedules"})
    private User user;

    private List<Task> tasks = new ArrayList<>();

    public Schedule(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", tasks=" + tasks.size() +
                '}';
    }
}
