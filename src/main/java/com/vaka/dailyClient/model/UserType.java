package com.vaka.dailyClient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserType {
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @JsonIgnore
    private List<User> users;

    public UserType() {
    }

    public UserType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserType{");

        sb.append(String.format("id=%d", id));
        sb.append(String.format(", name=%s", name));
        sb.append(", users=");

        if (users != null) {
            sb.append(users);
        }

        return sb.toString();
    }
}
