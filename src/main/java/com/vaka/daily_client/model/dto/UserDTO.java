package com.vaka.daily_client.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotEmpty
    @Size(max = 100)
    private String login;

    @NotEmpty
    @Size(max = 100)
    private String firstName;

    @NotEmpty
    @Size(max = 100)
    private String password;

    @NotEmpty
    @Size(max = 100)
    private String secondName;

    @Size(max = 100)
    private String patronymic;
}
