package com.vaka.daily_client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserType {
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @JsonIgnore
    private List<User> users;

    public UserType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
