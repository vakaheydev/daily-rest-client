package com.vaka.daily_client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @JsonIgnoreProperties({"user"})
    private List<Schedule> schedules = new ArrayList<>();

    private Long telegramId;

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String login, String password, String firstName, String secondName, String patronymic,
                Long telegramId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.telegramId = telegramId;
    }

    public User(String login, String password, String firstName, String secondName, String patronymic) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", userType=" + (userType == null ? "undefined" : userType.getName()) +
                ", telegramId(optional)=" + telegramId +
                ", schedules=" + schedules.size() +
                '}';
    }
}
