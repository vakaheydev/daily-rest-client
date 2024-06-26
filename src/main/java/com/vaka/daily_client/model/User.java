package com.vaka.daily_client.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
        if (userType == null) {
            return "User{" +
                    "id=" + id +
                    ", login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", secondName='" + secondName + '\'' +
                    ", patronymic='" + patronymic + '\'' +
                    ", userType=" + "null" +
                    ", schedules=" + schedules +
                    '}';
        }

        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", userType=" + userType.getName() +
                ", schedules=" + schedules +
                '}';
    }
}
