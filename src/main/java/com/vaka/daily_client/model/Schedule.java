package com.vaka.daily_client.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Schedule {
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @JsonBackReference
    private User user;

    @JsonManagedReference
    private List<Task> tasks;

    public Schedule() {
    }

    public Schedule(String name, User user) {
        this.name = name;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user.getLogin() +
                ", tasks=" + tasks +
                '}';
    }
}
