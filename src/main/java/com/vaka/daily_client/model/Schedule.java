package com.vaka.daily_client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vaka.daily_client.model.serialization.UserIdSerializer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class Schedule {
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @JsonSerialize(using = UserIdSerializer.class)
    @JsonIgnoreProperties({"schedules"})
    private User user;

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<Task> tasks = new ArrayList<>();

    public Schedule(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Schedule(Integer id, String name, User user, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.tasks = (tasks == null ? new ArrayList<>() : tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @JsonProperty("userId")
    public Integer getUserId() {
        Objects.requireNonNull(user);

        return user.getId();
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
