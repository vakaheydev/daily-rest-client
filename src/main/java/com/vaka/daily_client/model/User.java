package com.vaka.daily_client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(value = {"schedules"}, allowSetters = true)
public class User {
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    private String login;

    @NotEmpty
    @Size(max = 100)
    private String password;

    @NotEmpty
    @Size(max = 100)
    private String firstName;

    @NotEmpty
    @Size(max = 100)
    private String secondName;

    @Size(max = 100)
    private String patronymic;

    private UserType userType;

    @JsonManagedReference
    private List<Schedule> schedules = new ArrayList<>();

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String login, String password, String firstName, String secondName, String patronymic,
                UserType userType) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.userType = userType;
    }

    public User(String login, String password, String firstName, String secondName, String patronymic) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
    }

    public User() {
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User{");

        sb.append(String.format("id=%d", id));
        sb.append(String.format(", login='%s'", login));
        sb.append(String.format(", password='%s'", password));
        sb.append(String.format(", firstName='%s'", firstName));
        sb.append(String.format(", secondName='%s'", secondName));
        sb.append(String.format(", patronymic='%s'", patronymic));
        sb.append(", userType=");

        if (userType != null) {
            sb.append(String.format("'%s'", userType.getName()));
        }

        sb.append(", schedules=");

        if (schedules != null) {
            sb.append(schedules);
        }

        sb.append("}");

        return sb.toString();
    }
}
