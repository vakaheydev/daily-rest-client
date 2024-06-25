package com.vaka.daily.model;

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
}
